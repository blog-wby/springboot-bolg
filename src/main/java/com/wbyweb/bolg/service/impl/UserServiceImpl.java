package com.wbyweb.bolg.service.impl;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbyweb.bolg.common.Const;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.mapper.IBaseMapper;
import com.wbyweb.bolg.mapper.UserMapper;
import com.wbyweb.bolg.po.User;
import com.wbyweb.bolg.service.AbstractBaseService;
import com.wbyweb.bolg.service.UserService;
import com.wbyweb.bolg.util.MD5util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractBaseService<User> implements UserService {

    @Resource
    public UserMapper mapper;

    @Override
    protected IBaseMapper<User> getMapper() {
        return this.mapper;
    }

    //登陆
    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount=mapper.checkUserExist(username);
        if(resultCount==0){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        //密码登录MD5
        String md5password=MD5util.GetMD5String(password);
        User user=mapper.selectLogin(username, md5password);
        if(user==null){
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setUserPwd(null);//相应user 清空密码
        return ServerResponse.createBySuccessMsg("登录成功",user);
    }
    //注册
    @Override
    public ServerResponse<String> register(User user) {
        //校验用户名存在
        ServerResponse<String> validResponse=this.checkValid(user.getUserName(),Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        //校验邮箱是否存在
        validResponse=this.checkValid(user.getUserEmail(),Const.EMAIL);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        //MD5加密
        if(user.getUserPwd() != null){
            user.setUserPwd(MD5util.GetMD5String(user.getUserPwd()));
        }
        int resultCount = mapper.insertSelective(user);
        if(resultCount==0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMsg("注册成功");
    }

    @Override
    public ServerResponse<String> wxregister(User user) {
        return null;
    }

    //校验
    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if(type.trim()!=null){
            //开始校验户名
            if(Const.USERNAME.equals(type)){
                int resultCount=mapper.checkUsername(str);
                if(resultCount>0){
                    return ServerResponse.createByErrorMessage("用户名已经存在");
                }
            }
            //开始校验邮箱
            if(Const.EMAIL.equals(type)){
                int resultCount=mapper.checkEmail(str);
                if(resultCount>0){
                    return ServerResponse.createByErrorMessage("邮箱已经存在");
                }
            }
        }else{
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMsg("注册成功");
    }

    @Override
    public ServerResponse<String> forgetGetQuestion(String username) {
        return null;
    }

    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        return null;
    }

    @Override
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        return null;
    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user) {
        return null;
    }

    @Override
    public ServerResponse<User> upateInformation(User user) {
        return null;
    }

    @Override
    public ServerResponse<User> getInformation(Integer userId) {
        User user = mapper.selectByPrimaryKey(userId);
        return ServerResponse.createBySuccess(user);
    }

    @Override
    public ServerResponse<User> checkAdminRole(User user) {
        return null;
    }

    @Override
    public User wxregister(String openId) {
        return null;
    }

    //根据用户名查询用户
    @Override
    public PageInfo<?> getUserList(String username, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isNotEmpty(username)) {
            username = new StringBuilder().append("%").append(username).append("%").toString();
        }
        List<User> userListByName = mapper.getUserListByName(username);
        PageInfo pageResult = new PageInfo(userListByName);
        return pageResult;
    }
}
