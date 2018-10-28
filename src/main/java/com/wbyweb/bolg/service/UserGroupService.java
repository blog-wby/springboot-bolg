package com.wbyweb.bolg.service;

import com.github.pagehelper.PageInfo;
import com.wbyweb.bolg.mapper.IBaseMapper;
import com.wbyweb.bolg.po.UserGroup;

public interface UserGroupService  extends IBaseMapper<UserGroup> {
    /**
     * 获取用户组列表 或者根据用户组名查询用户组列表
     * @param groupName
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<?> getUserGroupListByName(String groupName, int pageNum, int pageSize);
}
