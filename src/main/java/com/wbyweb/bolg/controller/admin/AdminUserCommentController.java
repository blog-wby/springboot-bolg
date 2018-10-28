package com.wbyweb.bolg.controller.admin;

import com.github.pagehelper.PageInfo;
import com.wbyweb.bolg.service.UserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequestMapping("/comment")
@Controller
public class AdminUserCommentController {
    @Autowired
    UserCommentService userCommentService;

    @RequestMapping("/index")
    public String comment(Model model, HttpSession session,
                          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, String content){
        PageInfo pageInfo = userCommentService.seartchUserCommentListByContent(pageNum, pageSize, content);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/comment";
    }
}
