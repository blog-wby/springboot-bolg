package com.wbyweb.bolg.mapper;

import com.wbyweb.bolg.po.Article;
import com.wbyweb.bolg.po.ArticleWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleMapper  extends IBaseMapper<Article>{

    /**
     * 添加或者保存
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    /**
     * 根据条件查询文章列表
     * @return
     */
    List<ArticleWithBLOBs> searchIndexArticleByArticlename(@Param("articlename")String articlename);




    /**
     *
     * @param sortArticleId
     * @return
     */
    List<ArticleWithBLOBs> searchIndexArticleBySortid(@Param("sortArticleId")Integer sortArticleId);

    //根据ID查询文章详细信息
    ArticleWithBLOBs searchIndexArticleByArticleId(@Param("articleId")Integer articleId);

    //更新文章查看数量
    void updateArticleClickBySortid(@Param("articleId")Integer articleId);

    //查询推荐文章
    List<Article> searchIndexArticleByArticleSupport();

    //查询最新评论文章
    List<Article> searchNewestUserCommentArticle();

    /**
     * 查询所有文章
     * @return
     */
    List<ArticleWithBLOBs> searchAllArticleList();


    /**
     * 查询所有标签
     */
    //SELECT tagcloud FROM `article`
    List<Article> searchAlltagcloud();

    /**
     * 根据用户ID查询文章列表
     * @param userid
     * @return
     */
    List<ArticleWithBLOBs> searchIndexArticleByUserId(@Param("articlename")String articlename,@Param("userid")Integer userid);
}