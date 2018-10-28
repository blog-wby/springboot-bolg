package com.wbyweb.bolg.service;

import com.github.pagehelper.PageInfo;

public interface NoticeService {

    PageInfo searchNoticeBynoticetitle(int pageNum, int pageSize, String noticetitle);
}
