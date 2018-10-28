package com.wbyweb.bolg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.wbyweb.bolg.common.Const;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.dao.SearchArticleDao;
import com.wbyweb.bolg.mapper.ArticleMapper;
import com.wbyweb.bolg.mapper.IBaseMapper;
import com.wbyweb.bolg.po.Article;
import com.wbyweb.bolg.po.ArticleWithBLOBs;
import com.wbyweb.bolg.po.SearchResult;
import com.wbyweb.bolg.service.AbstractBaseService;
import com.wbyweb.bolg.service.ArticleService;
import com.wbyweb.bolg.util.DateUtil;
import com.wbyweb.bolg.util.JsonUtils;
import com.wbyweb.bolg.util.RedisUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArticleServiceImpl extends AbstractBaseService<Article> implements ArticleService{

    @Resource
    ArticleMapper mapper;
    @Autowired
    private SolrClient client;

    @Autowired
    private SearchArticleDao searchArticleDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    RedisUtil redisUtil;

    @Override
    protected IBaseMapper<Article> getMapper() {
        return this.mapper;
    }

    @Override
    public ServerResponse<String> addArticle(Article article) {
        //描述过长处理
        article.setArticledescription(article.getArticledescription().length()>200?article.getArticledescription().substring(0,200):article.getArticledescription());
        //标题过长处理
        article.setArticleName(article.getArticleName().length()>36?article.getArticleName().substring(0,36):article.getArticleName());
        if(article.getArticleId()!=null){//修改
            if(article.getArticleImgUrl().length()>50){
                article.setArticleImgUrl(article.getArticleImgUrl().substring(article.getArticleImgUrl().length()-48,article.getArticleImgUrl().length()));
            }
            int resultCount = mapper.updateByPrimaryKeySelective(article);
            if(resultCount==0){
                return ServerResponse.createByErrorMessage("修改失败！");
            }
            return ServerResponse.createBySuccessMsg("修改成功！");
        }else{//添加
            article.setArticleTime(new Date());//创建时间
            int resultCount = mapper.insertSelective(article);
            if(resultCount==0){
                return ServerResponse.createByErrorMessage("保存失败！");
            }
            return ServerResponse.createBySuccessMsg("保存成功！");
        }
    }

    @Override
    public PageInfo searchIndexArticleByArticlename(int pageNum, int pageSize,String articleName) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleWithBLOBs> articleList=mapper.searchIndexArticleByArticlename(articleName);
        PageInfo pageResult = new PageInfo(articleList);
        return pageResult;
    }

    @Override
    public PageInfo searchIndexArticleBySortid(int pageNum, int pageSize, Integer sortid) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleWithBLOBs> articleList = mapper.searchIndexArticleBySortid(sortid);
        PageInfo pageResult = new PageInfo(articleList);
        return pageResult;
    }

    //根据ID查询文章详细信息
    @Override
    public ServerResponse searchIndexArticleBySortid(Integer articleId) {
        //查询文章
        ArticleWithBLOBs articleList = mapper.searchIndexArticleByArticleId(articleId);
        //标签云
        articleList.setTagclouds(Arrays.asList(articleList.getTagcloud().split(",")));
        return ServerResponse.createBySuccess(articleList);
    }

    //更新文章查看数量
    public void updateArticleClickBySortid(Integer articleId,HttpServletRequest request) {
        String CookieName = getCookie(request, Const.COOKIENAME);
        String uri = request.getRequestURI();
        Object o = redisUtil.get(CookieName+"-"+uri);
        if(o==null) {
            mapper.updateArticleClickBySortid(articleId);
        }
        redisUtil.set(CookieName+"-"+uri,uri,60*60);
    }

    @Override
    public List<Article> searchIndexArticleByArticleSupport() {
        return mapper.searchIndexArticleByArticleSupport();
    }

    @Override
    public PageInfo searchNewestUserCommentArticle(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> articles = mapper.searchNewestUserCommentArticle();
        PageInfo pageResult = new PageInfo(articles);
        return pageResult;
    }

    @Override
    public ServerResponse importAllArticle(){
        List<ArticleWithBLOBs> articles = mapper.searchAllArticleList();
        try {
            for(ArticleWithBLOBs article:articles){
                //创建一个SolrInputDocument对象
                SolrInputDocument document = new SolrInputDocument();
                document.setField("id", article.getArticleId());
                document.setField("article_name", article.getArticleName());
                document.setField("article_html", article.getArticleHtml());
                document.setField("article_img_url", article.getArticleImgUrl());
                document.setField("articleDescription", article.getArticledescription());
                document.setField("sort_article_id", article.getArticleSort().getSortArticleName());
                document.setField("tagcloud", article.getTagcloud());
                document.setField("user_name", article.getUser().getUserName());
                document.setField("user_id", article.getUser().getUserId());
                document.setField("total", article.getTotal());
                document.setField("article_click", article.getArticleClick());
                document.setField("article_time", DateUtil.dateToStr(article.getArticleTime(),DateUtil.FORMAT_YYY_MM_DD));
                //写入索引库
                client.add(document);
                client.commit("collection1");
            }
            return ServerResponse.createBySuccessMsg("数据倒入成功！");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ServerResponse.createByErrorMessage("数据倒入失败！");
    }

    //搜索功能
    @Override
    public SearchResult search(String queryString, int page, int rows) {
        try {
            //创建查询对象
            SolrQuery query = new SolrQuery();
            //设置查询条件
            if(StringUtils.isEmpty(queryString)){
                query.setQuery("*:*");
            }else{
                query.setQuery(queryString);
            }
            //设置分页
            query.setStart((page - 1) * rows);
            query.setRows(rows);
            //设置默认搜素域
            query.set("df", "item_keywords");
            query.addSort(new SolrQuery.SortClause("id", SolrQuery.ORDER.desc));
            //设置高亮显示
            query.setHighlight(true);
            query.addHighlightField("articleDescription");
            query.setHighlightSimplePre("<em style=\"color:red\">");
            query.setHighlightSimplePost("</em>");
            //执行查询
            SearchResult searchResult = searchArticleDao.search(query);
            //计算查询结果总页数
            long recordCount = searchResult.getRecordCount();
            long pageCount = recordCount / rows;
            if (recordCount % rows > 0) {
                pageCount++;
            }
            searchResult.setPageCount(pageCount);
            searchResult.setCurPage(page);

            return searchResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageInfo searchByUserId(String articlename,Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleWithBLOBs> articleList=mapper.searchIndexArticleByUserId(articlename,userId);
        PageInfo pageResult = new PageInfo(articleList);
        return pageResult;
    }

    @Override
    public Map<String,Integer> searchAllTagcloud() {
        Map<String,Integer> map= Maps.newHashMap();
        List<Article> articles = mapper.searchAlltagcloud();
        for(Article article:articles){
            String tagcloud = article.getTagcloud();
            if(!StringUtils.isEmpty(tagcloud)){
                String[] split = tagcloud.split(",");
                for (String s:split) {
                    if(map.containsKey(s)){
                        map.put(s,map.get(s)+1);
                    }else{
                        map.put(s,1);
                    }
                }
            }
        }

        return map;
    }

    public static String getCookie(HttpServletRequest request,String cookieName){
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cookieName)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
