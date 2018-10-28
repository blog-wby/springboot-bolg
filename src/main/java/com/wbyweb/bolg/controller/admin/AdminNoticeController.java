package com.wbyweb.bolg.controller.admin;

import com.github.pagehelper.PageInfo;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

//公告操作
@RequestMapping("/notice")
@Controller
public class AdminNoticeController {
    @Autowired
    NoticeService noticeService;

    @GetMapping("/index")
    public String notice(Model model, HttpSession session,
                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, String noticetitle){
        PageInfo pageInfo = noticeService.searchNoticeBynoticetitle(pageNum, pageSize, noticetitle);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/notice";
    }
    @ResponseBody
    @PostMapping("/notice")
    public ServerResponse noticeshow(Model model, HttpSession session,
                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, String noticetitle){
        PageInfo pageInfo = noticeService.searchNoticeBynoticetitle(pageNum, pageSize, noticetitle);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
