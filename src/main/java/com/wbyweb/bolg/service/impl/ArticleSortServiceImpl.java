package com.wbyweb.bolg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.mapper.ArticleSortMapper;
import com.wbyweb.bolg.mapper.IBaseMapper;
import com.wbyweb.bolg.po.ArticleSort;
import com.wbyweb.bolg.service.AbstractBaseService;
import com.wbyweb.bolg.service.ArticleSortService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class ArticleSortServiceImpl extends AbstractBaseService<ArticleSort> implements ArticleSortService {

    @Resource
    ArticleSortMapper mapper;

    @Override
    protected IBaseMapper<ArticleSort> getMapper() {
        return this.mapper;
    }

    @Override
    public PageInfo searchArticleSortBySortArticleName(int pageNum, int pageSize, String sortarticlename) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleSort> articleSorts = mapper.searchArticleSortBySortArticleName(sortarticlename);
        PageInfo pageResult = new PageInfo(articleSorts);
        return pageResult;
    }

    @Override
    public ServerResponse addArticleSort(ArticleSort articleSort) {
        int i=0;
        if(articleSort.getSortArticleId()==0) {
            i = mapper.updateByPrimaryKeySelective(articleSort);
        }else {
            articleSort.setSortArticleId(null);
            i = mapper.insertSelective(articleSort);
        }
        if(i==0){
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse delArticleSort(Integer sortArticleId) {
        int i = mapper.deleteByPrimaryKey(sortArticleId);
        if(i==0){
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public List<ArticleSort> searchArticleSortList() {
        return mapper.searchArticleSortList();
    }
}
