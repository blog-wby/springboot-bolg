package com.wbyweb.bolg.mapper;

import com.wbyweb.bolg.po.UserGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户组
 */
public interface UserGroupMapper extends IBaseMapper<UserGroup>{
    /**
     * 根据条件查询用户组列表
     * @param groupName
     * @return
     */
    List<UserGroup> getUserGroupListByName(@Param("groupName")String groupName);
}