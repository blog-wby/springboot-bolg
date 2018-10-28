package com.wbyweb.bolg.controller.gateway;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.wbyweb.bolg.common.Const;
import com.wbyweb.bolg.common.ResponseCode;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.po.*;
import com.wbyweb.bolg.service.ArticleService;
import com.wbyweb.bolg.service.ArticleSortService;
import com.wbyweb.bolg.service.NoticeService;
import com.wbyweb.bolg.service.UserCommentService;
import com.wbyweb.bolg.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleSortService articleSortService;
    @Autowired
    UserCommentService userCommentService;


    @Value("${ftp.server.http.prefix}")
    private String prefix;

    @GetMapping("/writearticle")
    public String gotowritearticle(HttpSession session,Model model){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            model.addAttribute(Const.MESSAGE,"用户未登录,请登录！");
            return "public/404";
        }
        //博客分类
        List<ArticleSort> articleSorts = articleSortService.searchArticleSortList();
        model.addAttribute("articleSorts",articleSorts);
        return "public/write-article";
    }
    /**
     * 详细文章查询
     * @param model
     * @param session
     * @return
     */
    @GetMapping(value="/detailedarticle/{articleid}")
    public String searchDetailedArticle(Model model, HttpSession session, @PathVariable("articleid")Integer articleid, HttpServletRequest request,HttpServletResponse response) {
        //查询详细文章
        ServerResponse serverResponse = articleService.searchIndexArticleBySortid(articleid);
        //查询评论
        List<Map<String, Object>> listMaps = userCommentService.seartchUserCommentListByArticleId(articleid);
        //最新评论文章
        PageInfo newestArticle = articleService.searchNewestUserCommentArticle(1, 4);
        model.addAttribute("ftpip",prefix);
        model.addAttribute("data",serverResponse);
        model.addAttribute("listMaps",listMaps);
        model.addAttribute("newestArticle",newestArticle);
        try{
            if(StringUtils.isEmpty(getCookie(request,Const.COOKIENAME))){
                Cookie cookie = new Cookie(Const.COOKIENAME,UuidUtil.getUUID());
                cookie.setPath("/");
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
            }
            articleService.updateArticleClickBySortid(articleid,request);
        }catch (Exception e){}

        return "public/detailed";
    }

    /**
     * 跳转致文章编辑页面
     * @param model
     * @param session
     * @param articleid
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/editDetailedArticle/{articleid}")
    public String editDetailedArticle(Model model, HttpSession session, @PathVariable("articleid")Integer articleid, HttpServletRequest request,HttpServletResponse response) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            model.addAttribute(Const.MESSAGE,"用户未登录,请登录！");
            return "public/404";
        }
        //查询详细文章
        ServerResponse serverResponse = articleService.searchIndexArticleBySortid(articleid);
        model.addAttribute("serverResponse",serverResponse);
        //博客分类
        List<ArticleSort> articleSorts = articleSortService.searchArticleSortList();
        model.addAttribute("articleSorts",articleSorts);
        model.addAttribute("ftpip",prefix);
        return "public/edit-article";
    }


    /**
     * 查询首页文章
     * @param model
     * @param articleName
     * @param pageNum
     * @return
     */
    @GetMapping(value="/index/{pageNum}")
    public String searchIndexArticle(Model model,String articleName,@PathVariable int pageNum){
        int pageSize=5;
        //首页文章
        SearchResult search = articleService.search(articleName, pageNum, pageSize);
        //推荐文章
        List<Article> articleslist = articleService.searchIndexArticleByArticleSupport();
        //最新评论文章
        PageInfo newestArticle = articleService.searchNewestUserCommentArticle(1, 8);

        model.addAttribute("ftpip",prefix);
        model.addAttribute("pageInfo",search);
        model.addAttribute("articleslist",articleslist);
        model.addAttribute("newestArticle",newestArticle);
        //标签云
        Map<String, Integer> tagclouds= articleService.searchAllTagcloud();
        model.addAttribute("tagclouds",tagclouds);
        //博客分类
        List<ArticleSort> articleSorts = articleSortService.searchArticleSortList();
        model.addAttribute("articleSorts",articleSorts);

        return "public/index";
    }

    /**
     * 搜索功能
     * @param model
     * @param articleName
     * @param pageNum
     * @return
     */
    @PostMapping(value="/solrsearch/{pageNum}")
    public String searchSolrArticle(Model model,String articleName,@PathVariable int pageNum){
        int pageSize=5;
        //文章列表
        SearchResult search = articleService.search(articleName, pageNum, pageSize);
        model.addAttribute("pageInfo",search);
        //推荐文章
        List<Article> articleslist = articleService.searchIndexArticleByArticleSupport();
        model.addAttribute("articleslist",articleslist);
        //最新评论文章
        PageInfo newestArticle = articleService.searchNewestUserCommentArticle(1, 8);
        model.addAttribute("newestArticle",newestArticle);
        //标签云
        Map<String, Integer> tagclouds= articleService.searchAllTagcloud();
        model.addAttribute("tagclouds",tagclouds);
        model.addAttribute("ftpip",prefix);
        //博客分类
        List<ArticleSort> articleSorts = articleSortService.searchArticleSortList();
        model.addAttribute("articleSorts",articleSorts);
        return "public/fullstack";
    }


    @GetMapping(value="/solrsearcht/{pageNum}/{articleName}")
    public String searchSolrArticlet(Model model,@PathVariable("articleName")String articleName,@PathVariable("pageNum") int pageNum){
        int pageSize=5;
        //文章列表
        SearchResult search = articleService.search(articleName, pageNum, pageSize);
        model.addAttribute("pageInfo",search);
        //推荐文章
        List<Article> articleslist = articleService.searchIndexArticleByArticleSupport();
        model.addAttribute("articleslist",articleslist);
        //最新评论文章
        PageInfo newestArticle = articleService.searchNewestUserCommentArticle(1, 8);
        model.addAttribute("newestArticle",newestArticle);
        //标签云
        Map<String, Integer> tagclouds= articleService.searchAllTagcloud();
        model.addAttribute("tagclouds",tagclouds);
        //博客分类
        List<ArticleSort> articleSorts = articleSortService.searchArticleSortList();
        model.addAttribute("articleSorts",articleSorts);

        model.addAttribute("ftpip",prefix);
        return "public/fullstack";
    }

    /**
     * 全栈信息查询
     * @param model
     * @param articleName
     * @param pageNum
     * @return
     */
    @GetMapping(value="/fullstack/{pageNum}")
    public String searchFullStackArticle(Model model,String articleName,@PathVariable("pageNum")int pageNum){
        int pageSize=5;
        //首页文章
        SearchResult search = articleService.search(articleName, pageNum, pageSize);
        //推荐文章
        List<Article> articleslist = articleService.searchIndexArticleByArticleSupport();
        //最新评论文章
        PageInfo newestArticle = articleService.searchNewestUserCommentArticle(1, 8);
        //标签云
        Map<String, Integer> tagclouds= articleService.searchAllTagcloud();

        //博客分类
        List<ArticleSort> articleSorts = articleSortService.searchArticleSortList();
        model.addAttribute("articleSorts",articleSorts);

        model.addAttribute("ftpip",prefix);
        model.addAttribute("pageInfo",search);
        model.addAttribute("articleslist",articleslist);
        model.addAttribute("newestArticle",newestArticle);
        model.addAttribute("tagclouds",tagclouds);
        return "public/fullstack";
    }

    //根据类型查询文章
    @ResponseBody
    @PostMapping(value="/fullstackbytype/{sortid}")
    public HashMap<String, Object> searchFullStackByType(Model model,HttpSession session,@PathVariable("sortid")Integer sortid, @RequestParam(value = "page", defaultValue = "1") int pageNum,@RequestParam(value = "limit", defaultValue = "20") int pageSize){
        PageInfo pageInfo = articleService.searchIndexArticleBySortid(pageNum, pageSize,sortid);
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("ftpip",prefix);
        map.put("pageInfo",pageInfo);
        return map;
    }

    //保存文章
    @PostMapping("/save")
    @ResponseBody
    public ServerResponse<?> saveArticle(ArticleWithBLOBs article, HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录！");
        }
        article.setUserId(user.getUserId());
        ServerResponse<String> register = articleService.addArticle(article);
        return register;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();//返回请求行中的资源名称
        String url = request.getRequestURL().toString();//获得客户端发送请求的完整url
        String ip = request.getRemoteAddr();//返回发出请求的IP地址
        String params = request.getQueryString();//返回请求行中的参数部分
        String host=request.getRemoteHost();//返回发出请求的客户机的主机名
        int port =request.getRemotePort();//返回发出请求的客户机的端口号。
        System.out.println(ip);
        System.out.println(url);
        System.out.println(uri);
        System.out.println(params);
        System.out.println(host);
        System.out.println(port);
    }

    /**
     * 获取浏览器Cookie中存的值
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookie(HttpServletRequest request,String cookieName){
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(cookieName)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
