package com.wbyweb.bolg.service;

import com.github.pagehelper.PageInfo;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.mapper.IBaseMapper;
import com.wbyweb.bolg.po.User;

import java.util.Map;

public interface UserService extends IBaseMapper<User> {

    //这里写除了增删改以外的方法


    /**
     * 用户登录方法
     * @param username
     * @param password
     * @return
     */
    ServerResponse<User> login(String username,String password);
    /**
     * 用户注册方法
     * @param user
     * @return
     */
    ServerResponse<String> register(User user);
    /**
     * 微信用户注册方法
     * @param user
     * @return
     */
    ServerResponse<String> wxregister(User user);
    /**
     * 验证用户名邮箱是否有效
     * @param str
     * @param type
     * @return
     */
    ServerResponse<String> checkValid(String str,String type);
    /**
     * 密码问题的获取
     * @param username
     * @return
     */
    ServerResponse<String> forgetGetQuestion(String username);
    /**
     * 校验问题答案是否正确
     * @param username
     * @param question
     * @param answer
     * @return
     */
    ServerResponse<String> checkAnswer(String username,String question,String answer);

    /**
     * 修改密码
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    ServerResponse<String> forgetRestPassword(String username,String passwordNew,String forgetToken);
    /**
     * 登录状态下修改密码
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);
    /**
     * 更新个人信息
     * @param user
     * @return
     */
    ServerResponse<User> upateInformation(User user);
    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    ServerResponse<User> getInformation(Integer userId);
    /**
     * 校验是否是管理员
     * @param user
     * @return
     */
    ServerResponse<User> checkAdminRole(User user);
    /**
     * 检查微信用户是否存在
     * @param openId
     * @return
     */
    User wxregister(String openId);
    /**
     * 获取用户列表 或者根据用户名查询用户列表
     * @param username
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo getUserList(String username, int pageNum, int pageSize);
}
