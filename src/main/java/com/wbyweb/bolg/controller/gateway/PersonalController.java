package com.wbyweb.bolg.controller.gateway;

import com.github.pagehelper.PageInfo;
import com.wbyweb.bolg.common.Const;
import com.wbyweb.bolg.common.ResponseCode;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.po.User;
import com.wbyweb.bolg.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequestMapping("/personal")
@Controller
public class PersonalController {
    @Autowired
    ArticleService articleService;
    @Value("${ftp.server.http.prefix}")
    private String prefix;

    @RequestMapping("/personalCenter")
    public String personalCenter(Model model, HttpSession session,
                                 @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,String articlename){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            model.addAttribute(Const.MESSAGE,"用户未登录,请登录！");
            return "public/404";
        }
        //根据用户ID查询文章并分页
        PageInfo pageInfo = articleService.searchByUserId(articlename, user.getUserId(), pageNum, pageSize);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("ftpip",prefix);
        return "/public/personal";
    }
}
