package com.wbyweb.bolg.service;

import com.github.pagehelper.PageInfo;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.po.ArticleSort;

import java.util.List;

public interface ArticleSortService {

    PageInfo searchArticleSortBySortArticleName(int pageNum, int pageSize, String articleName);

    ServerResponse addArticleSort(ArticleSort articleSort);

    ServerResponse delArticleSort(Integer sortArticleId);

    /**
     * 查询所有分类
     * @return
     */
    List<ArticleSort> searchArticleSortList();
}
