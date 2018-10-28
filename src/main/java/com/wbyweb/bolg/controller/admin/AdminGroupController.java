package com.wbyweb.bolg.controller.admin;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.wbyweb.bolg.service.UserGroupService;
import com.wbyweb.bolg.util.EncodingTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.Map;


@RequestMapping("/admingroup")
@Controller
public class AdminGroupController {

    @Autowired
    UserGroupService userGroupService;
    //查询组
    @GetMapping("/index")
    public String getUserGroupList(Model model,
            HttpSession session, String groupname,
            @RequestParam(value = "page", defaultValue = "1") int pageNum,
            @RequestParam(value = "limit", defaultValue = "5") int pageSize){
        PageInfo<?> pageInfo = userGroupService.getUserGroupListByName(EncodingTool.encodeStr(groupname), pageNum, pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/manage-usergroup";
    }
}
