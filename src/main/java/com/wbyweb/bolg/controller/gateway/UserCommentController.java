package com.wbyweb.bolg.controller.gateway;

import com.wbyweb.bolg.common.Const;
import com.wbyweb.bolg.common.ResponseCode;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.po.User;
import com.wbyweb.bolg.po.UserComment;
import com.wbyweb.bolg.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/usercomment")
@RestController
public class UserCommentController {

    @Autowired
    UserCommentService userCommentService;

    @PostMapping("/save")
    public ServerResponse<?> saveArticle(UserComment userComment, HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录！");
        }
        userComment.setUid(user.getUserId());
        ServerResponse<String> register = userCommentService.insertUserCommentService(userComment);
        return register;
    }
}
