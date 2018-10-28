package com.wbyweb.bolg.mapper;

import com.wbyweb.bolg.po.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//公告
public interface NoticeMapper extends IBaseMapper<Notice>{

    List<Notice> searchNoticeByNoticetitle(@Param("noticetitle")String noticetitle);
}