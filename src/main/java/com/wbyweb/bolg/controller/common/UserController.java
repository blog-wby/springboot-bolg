package com.wbyweb.bolg.controller.common;

import com.google.code.kaptcha.Constants;
import com.wbyweb.bolg.common.Const;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.po.User;
import com.wbyweb.bolg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    JavaMailSender mailSender;

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return "redirect:/article/index/1";
    }
    //用户登录
    @PostMapping("/login")
    public String login(Model model, @RequestParam(value = "kaptcha",required = true)String kaptcha,
                        String userName, String userPwd, HttpSession session){
        //用户输入的验证码的值
        String code = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //校验验证码是否正确
        if (kaptcha == null || !kaptcha.equals(code)) {
            model.addAttribute("msg","验证码输入有误！");
            model.addAttribute("userName",userName);
            model.addAttribute("userPwd",userPwd);
            return "public/login";
        }
        ServerResponse<User> response= userService.login(userName, userPwd);
        if(response.isSuccess()){
            User user=response.getData();
            /*用户名过长处理*/
            String userName1 = user.getUserName();
            userName1=user.getUserName().length()>4?userName1.substring(0,3)+"..":user.getUserName();
            user.setUserName(userName1);
            session.setAttribute(Const.CURRENT_USER, user);
            model.addAttribute(Const.CURRENT_USER, user);
            return "redirect:/article/index/1";
        }else{
            model.addAttribute("msg",response.getMsg());
            model.addAttribute("userName",userName);
            model.addAttribute("userPwd",userPwd);
            return "public/login";
        }
    }


    //用户注册
    @PostMapping("/register")
    public String register(
            @RequestParam(value = "kaptcha", required = true)String kaptcha,
            User user,Model model,HttpSession session){
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //校验验证码是否正确
        if (kaptcha == null || !kaptcha.equals(code)) {
            model.addAttribute("user",user);
            model.addAttribute("msg","验证码输入有误！");
            return "/public/register";
        }
        ServerResponse<String> register = userService.register(user);
        if(0==register.getStatus()){
            try {
                final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
                final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setFrom("weibiaoyi@126.com");
                message.setTo("18210493942@163.com");
                message.setSubject("恭喜来新人了");
                message.setText("新人："+user.getUserName());
                this.mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            model.addAttribute("msg",register.getMsg());
            return "/public/login";
        }else{
            model.addAttribute("msg",register.getMsg());
            model.addAttribute("user",user);
            return "/public/register";
        }
    }

    @GetMapping("/userinfo")
    @ResponseBody
    public ServerResponse<User> upate_information(){
        ServerResponse<User> information = userService.getInformation(1);
        return information;
    }
}
