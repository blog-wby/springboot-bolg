package com.wbyweb.bolg.controller.admin;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.wbyweb.bolg.common.Const;
import com.wbyweb.bolg.common.ResponseCode;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.po.ArticleSort;
import com.wbyweb.bolg.po.User;
import com.wbyweb.bolg.service.ArticleSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
@RequestMapping("/category")
@Controller
public class AdminArticleSortController {

    @Autowired
    ArticleSortService articleSortService;

    @GetMapping(value="/index")
    public String articleSortList(Model model, HttpSession session, String sortarticlename,
                                            @RequestParam(value = "page", defaultValue = "1") int pageNum,
                                            @RequestParam(value = "limit", defaultValue = "10") int pageSize){
        PageInfo pageInfo = articleSortService.searchArticleSortBySortArticleName(pageNum, pageSize, sortarticlename);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/category";
    }

    @PostMapping(value="/add")
    public String addArticleSort(Model model, HttpSession session,ArticleSort articleSort){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            model.addAttribute(Const.MESSAGE,"用户未登录,请登录！");
            return "public/404";
        }
        articleSort.setUserId(user.getUserId());
        articleSortService.addArticleSort(articleSort);
        return  "redirect:/category/index";
    }

    @GetMapping(value="/del{sortArticleId}")
    public String delArticleSort(Model model, HttpSession session,@PathVariable("sortArticleId")Integer sortArticleId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            model.addAttribute(Const.MESSAGE,"用户未登录,请登录！");
            return "public/404";
        }
        articleSortService.delArticleSort(sortArticleId);
        return  "redirect:category/index";
    }
}
