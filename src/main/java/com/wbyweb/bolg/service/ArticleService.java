package com.wbyweb.bolg.service;

import com.github.pagehelper.PageInfo;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.po.Article;
import com.wbyweb.bolg.po.SearchResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ArticleService {

    //添加
    ServerResponse<String> addArticle(Article article);


    //指定名称查询文章
    PageInfo searchIndexArticleByArticlename(int pageNum, int pageSize,String articleName);

    //指定类型查询文章
    PageInfo searchIndexArticleBySortid(int pageNum, int pageSize,Integer sortid);

    //根据ID查询文章详细信息
    ServerResponse searchIndexArticleBySortid(Integer articleId);

    /**
     * 更新文章阅读数量
     * @param articleId
     */
    void updateArticleClickBySortid(Integer articleId,HttpServletRequest request);

    /**
     * 查询博主推荐文章
     * @return
     */
    List<Article> searchIndexArticleByArticleSupport();

    /**
     * 查询最新评论文章
     * @return
     */
    PageInfo searchNewestUserCommentArticle(int pageNum, int pageSize);

    /**
     * 倒入所有文章
     * @return
     */
    ServerResponse importAllArticle();

    /**
     * 搜索功能
     * @param queryString
     * @param pageSize
     * @param pageNum
     * @return
     */
    SearchResult search(String queryString, int pageSize, int pageNum);

    /**
     * 根据用户ID搜索文章
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo searchByUserId(String articlename,Integer userId,int pageNum, int pageSize);

    /**
     * 分类所有标签
     * @return
     */
    Map<String,Integer> searchAllTagcloud();

}
