package com.wbyweb.bolg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbyweb.bolg.mapper.IBaseMapper;
import com.wbyweb.bolg.mapper.UserGroupMapper;
import com.wbyweb.bolg.po.UserGroup;
import com.wbyweb.bolg.service.AbstractBaseService;
import com.wbyweb.bolg.service.UserGroupService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class UserGroupServiceImpl extends AbstractBaseService<UserGroup> implements UserGroupService {

    @Resource
    public UserGroupMapper mapper;
    @Override
    protected IBaseMapper<UserGroup> getMapper() {
        return this.mapper;
    }

    @Override
    public PageInfo<?> getUserGroupListByName(String groupname, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isNotEmpty(groupname)) {
            groupname = new StringBuilder().append("%").append(groupname).append("%").toString();
        }
        List<UserGroup> userList= mapper.getUserGroupListByName(groupname);
        PageInfo pageResult = new PageInfo(userList);
        return pageResult;
    }
}
