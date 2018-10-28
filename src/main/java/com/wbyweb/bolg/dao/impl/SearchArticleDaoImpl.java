package com.wbyweb.bolg.dao.impl;

import com.wbyweb.bolg.dao.SearchArticleDao;
import com.wbyweb.bolg.po.*;
import com.wbyweb.bolg.util.DateUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Repository
public class SearchArticleDaoImpl implements SearchArticleDao {

    @Autowired
    private SolrClient client;

    @Override
    public SearchResult search(SolrQuery query) throws Exception {
        //返回值对象
        SearchResult result = new SearchResult();
        //根据查询条件查询索引库
        QueryResponse queryResponse = client.query(query);
        //取查询结果
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        //取查询结果总数量
        result.setRecordCount(solrDocumentList.getNumFound());
        //商品列表
        List<ArticleWithBLOBs> itemList = new ArrayList<ArticleWithBLOBs>();
        //取高亮显示
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        //取商品列表
        for (SolrDocument solrDocument : solrDocumentList) {
            //创建一文章对象
            ArticleWithBLOBs item = new ArticleWithBLOBs();
            Short id=new Short(solrDocument.get("id").toString());
            item.setArticleId(id);
            //取高亮显示的结果
            List<String> list = highlighting.get(solrDocument.get("id")).get("article_name");
            String article_name = "";
            if (list != null && list.size()>0) {
                article_name = list.get(0);
            } else {
                article_name = (String) solrDocument.get("article_name");
            }
            item.setArticleName(article_name);
            item.setArticleImgUrl(solrDocument.get("article_img_url").toString());
            item.setArticledescription(solrDocument.get("articleDescription").toString());
            item.setTagcloud(solrDocument.get("tagcloud").toString());
            item.setArticleClick(Integer.valueOf(solrDocument.get("article_click").toString()));
            User user=new User();
            user.setUserName(solrDocument.get("user_name").toString());
            item.setUser(user);
            UserComment userComment=new UserComment();
            userComment.setTotal(Integer.valueOf(solrDocument.get("total").toString()));
            item.setUserComment(userComment);
            item.setArticleTime(DateUtil.strToDate((String)solrDocument.get("article_time")));
            ArticleSort articleSort=new ArticleSort();
            articleSort.setSortArticleName(solrDocument.get("sort_article_id").toString());
            item.setArticleSort(articleSort);
            //添加的商品列表
            itemList.add(item);
        }
        result.setItemList(itemList);
        return result;
    }
}
