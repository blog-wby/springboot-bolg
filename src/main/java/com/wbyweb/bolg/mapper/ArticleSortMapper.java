package com.wbyweb.bolg.mapper;

import com.wbyweb.bolg.po.ArticleSort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章分类
 */
public interface ArticleSortMapper extends IBaseMapper<ArticleSort>{

    List<ArticleSort> searchArticleSortBySortArticleName(@Param("sortarticlename")String sortarticlename);

    List<ArticleSort> searchArticleSortList();
}