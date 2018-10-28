package com.wbyweb.bolg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.mapper.FriendlyLinkMapper;
import com.wbyweb.bolg.mapper.IBaseMapper;
import com.wbyweb.bolg.po.FriendlyLink;
import com.wbyweb.bolg.service.AbstractBaseService;
import com.wbyweb.bolg.service.FriendlyLinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class FriendlyLinkServiceImpl extends AbstractBaseService<FriendlyLink> implements FriendlyLinkService {

    @Resource
    FriendlyLinkMapper mapper;
    @Override
    protected IBaseMapper<FriendlyLink> getMapper() {
        return this.mapper;
    }

    @Override
    public PageInfo searchFriendlyLinkBylinkName(int pageNum, int pageSize, String linkName) {
        PageHelper.startPage(pageNum, pageSize);
        List<FriendlyLink> friendlyLinks = mapper.searchFriendlyLinkBylinkName(linkName);
        PageInfo pageResult = new PageInfo(friendlyLinks);
        return pageResult;
    }

    @Override
    public ServerResponse insertFriendlyLink(FriendlyLink friendlyLink) {
        int i =0;
        if(friendlyLink.getLinkId()==0){
            i = mapper.insertSelective(friendlyLink);
            if(i>0){
                return ServerResponse.createBySuccessMsg("添加成功！");
            }
            return ServerResponse.createBySuccessMsg("添加失败！");
        }else{
            i = mapper.updateByPrimaryKeySelective(friendlyLink);
            if(i>0){
                return ServerResponse.createBySuccessMsg("修改成功！");
            }
            return ServerResponse.createBySuccessMsg("修改失败！");
        }

    }

    @Override
    public ServerResponse delFriendlyLinkById(Integer linkId) {
        int i = mapper.deleteByPrimaryKey(linkId);
        if(i>0){
            return ServerResponse.createBySuccessMsg("删除成功！");
        }
        return ServerResponse.createBySuccessMsg("删除失败！");
    }

    @Override
    public ServerResponse searchFriendlyLinkBylinkId(Integer linkId) {
        FriendlyLink friendlyLink = mapper.selectByPrimaryKey(linkId);
        if(friendlyLink!=null){
            return ServerResponse.createBySuccess(friendlyLink);
        }
        return ServerResponse.createByErrorMessage("数据不存在！");

    }
}
