package com.wbyweb.bolg.controller.admin;

import com.github.pagehelper.PageInfo;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.po.FriendlyLink;
import com.wbyweb.bolg.service.FriendlyLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/afriendlylink")
@Controller
public class AdminFriendlyLinkController {

    @Autowired
    FriendlyLinkService friendlyLinkService;

    @RequestMapping("/index")
    public String flink(Model model, HttpSession session,
                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, String linkName){
        PageInfo pageInfo = friendlyLinkService.searchFriendlyLinkBylinkName(pageNum, pageSize, linkName);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/flink";
    }

    @GetMapping("/addflink")
    public String addflink(Model model,HttpSession session){
        return "/admin/add-flink";
    }
    @PostMapping("/add")
    public String add(Model model,HttpSession session,FriendlyLink friendlyLink){
        ServerResponse serverResponse = friendlyLinkService.insertFriendlyLink(friendlyLink);
        return "redirect:afriendlylink/index";
    }
    //跳转至删除页面
    @GetMapping("/updflink/{linkId}")
    public String updflink(Model model,HttpSession session,@PathVariable("linkId")Integer linkId){
        ServerResponse serverResponse = friendlyLinkService.searchFriendlyLinkBylinkId(linkId);
        model.addAttribute("friendlyLink",serverResponse.getData());
        return "admin/add-flink";
    }



    @ResponseBody
    @PostMapping("/del")
    public ServerResponse del(Model model,HttpSession session,Integer linkId){
        ServerResponse serverResponse = friendlyLinkService.delFriendlyLinkById(linkId);
        return serverResponse;
    }
}
