package com.wbyweb.bolg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbyweb.bolg.mapper.IBaseMapper;
import com.wbyweb.bolg.mapper.NoticeMapper;
import com.wbyweb.bolg.po.Notice;
import com.wbyweb.bolg.service.AbstractBaseService;
import com.wbyweb.bolg.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeServiceImpl extends AbstractBaseService<Notice> implements NoticeService {

    @Resource
    NoticeMapper mapper;

    @Override
    protected IBaseMapper<Notice> getMapper() {
        return this.mapper;
    }


    @Override
    public PageInfo searchNoticeBynoticetitle(int pageNum, int pageSize, String noticetitle) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> notices = mapper.searchNoticeByNoticetitle(noticetitle);
        PageInfo pageResult = new PageInfo(notices);
        return pageResult;
    }
}
