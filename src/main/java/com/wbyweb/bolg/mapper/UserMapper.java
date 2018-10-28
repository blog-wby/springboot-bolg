package com.wbyweb.bolg.mapper;

import com.wbyweb.bolg.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 用户
 */
public interface UserMapper extends IBaseMapper<User> {
    /**
     * 根据条件查询用户列表
     * @param userName
     * @return
     */
    List<User> getUserListByName(@Param("userName")String userName);

    /**
     * 根据OpenId检查用户是否已经注册
     * @param openId
     * @return
     */
    //User checkUserByOpenId(String openId);

    /**
     * 检查用户名是否存在
     * @param username
     * @return
     */
    int checkUsername(String username);


    /**
     * 用户登录
     * @param userName
     * @param userPwd
     * @return
     */
    User selectLogin(@Param("userName")String userName, @Param("userPwd")String userPwd);
    /**
     * 检查邮箱是否存在
     * @param email
     * @return
     */
    int checkEmail(String email);

    /**
     * 检查用户是否存在
     * @param userinfo
     * @return
     */
    int checkUserExist(@Param("userinfo")String userinfo);
    /**
     * 检查电话是否存在
     * @param phone
     * @return
     */
    //int checkPhone(String phone);

    /**
     * 密码问题的获取
     * @param username
     * @return
     */
    //String selectQuestionByUsername(String username);
    /**
     * 验证问题是否正确
     * @param username
     * @param question
     * @param answer
     * @return
     */
    //int checkAnswer(@Param("username")String username,@Param("question")String question,@Param("answer")String answer);
    /**
     * 忘记密码修改密码
     * @param username
     * @param passwordNew
     * @return
     */
    //int updatePasswordByusername(@Param("username")String username,@Param("passwordNew")String passwordNew);
    /**
     * 检查密码是否正确
     * @param password
     * @param userId
     * @return
     */
    //int checkPassword(@Param(value="password")String password, @Param("userId")Integer userId);

}