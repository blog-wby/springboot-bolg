package com.wbyweb.bolg.controller.admin;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.wbyweb.bolg.service.UserService;
import com.wbyweb.bolg.util.EncodingTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/adminuser")
public class AdminUserController {

    @Autowired
    UserService userService;

    //用户信息
    @GetMapping("/index")
    public String userList(Model model,HttpSession session, String userName,
                                   @RequestParam(value = "page", defaultValue = "1") int pageNum,
                                   @RequestParam(value = "limit", defaultValue = "5") int pageSize){
        Map<String,Object> resultMap=Maps.newHashMap();
        PageInfo<?> pageInfo = userService.getUserList(EncodingTool.encodeStr(userName), pageNum, pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/manage-user";
    }

}
