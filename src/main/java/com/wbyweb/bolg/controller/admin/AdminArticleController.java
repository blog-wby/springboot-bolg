package com.wbyweb.bolg.controller.admin;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RequestMapping("/adminarticle")
@Controller
public class AdminArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 查询文章
     * @param session
     * @return
     */
    @GetMapping(value="/index")
    public String searchArticle(Model model, HttpSession session, String articleName,
                                     @RequestParam(value = "page", defaultValue = "1") int pageNum,
                                     @RequestParam(value = "limit", defaultValue = "10") int pageSize){
        PageInfo pager = articleService.searchIndexArticleByArticlename(pageNum, pageSize, articleName);
        model.addAttribute("pager",pager);
        return "admin/article";
    }

    /**
     * 导入商品数据到索引库
     */
    @ResponseBody
    @RequestMapping("/importall")
    public ServerResponse importAllItems() {
        ServerResponse result = articleService.importAllArticle();
        return result;
    }

}
