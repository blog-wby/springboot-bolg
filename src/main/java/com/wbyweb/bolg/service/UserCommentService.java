package com.wbyweb.bolg.service;

import com.github.pagehelper.PageInfo;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.po.UserComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户评论
 */
public interface UserCommentService {

    ServerResponse insertUserCommentService(UserComment userComment);

    List<Map<String, Object>> seartchUserCommentListByArticleId(@Param("articleId")Integer articleId);

    PageInfo seartchUserCommentListByContent(int pageNum, int pageSize, String content);
}
