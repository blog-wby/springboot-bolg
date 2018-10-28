package com.wbyweb.bolg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // super.addViewControllers(registry);
        //浏览器发送 /atguigu 请求来到 success
        registry.addViewController("/atguigu").setViewName("success");
    }
    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //前台
                registry.addViewController("/").setViewName("commons/startblog");
                registry.addViewController("/index").setViewName("commons/startblog");
                registry.addViewController("/bootstrap-test.html").setViewName("commons/bootstrap-test");
                registry.addViewController("/about.html").setViewName("public/about");
                registry.addViewController("/time.html").setViewName("public/time");
                registry.addViewController("/upload.html").setViewName("public/upload");
                registry.addViewController("/login").setViewName("public/login");
                registry.addViewController("/register").setViewName("public/register");
                registry.addViewController("/fullstack.html").setViewName("public/fullstack");

            }
        };

        return adapter;
    }


}
