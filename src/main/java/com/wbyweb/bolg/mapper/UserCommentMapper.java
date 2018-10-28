package com.wbyweb.bolg.mapper;

import com.wbyweb.bolg.po.UserComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户评论
 */
public interface UserCommentMapper extends IBaseMapper<UserComment>{
    /**
     * 根据文章ID查询所有评论信息
     * @param articleId
     * @return
     */
    List<UserComment> seartchUserCommentListByArticleId(@Param("articleId")Integer articleId);

    /**
     * 根据评论内容查询评论信息
     * @param content
     * @return
     */
    List<UserComment> seartchUserCommentListByContent(@Param("content")String content);

}