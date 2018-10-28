package com.wbyweb.bolg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.mapper.IBaseMapper;
import com.wbyweb.bolg.mapper.UserCommentMapper;
import com.wbyweb.bolg.po.UserComment;
import com.wbyweb.bolg.service.AbstractBaseService;
import com.wbyweb.bolg.service.UserCommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class UserCommentServiceImpl extends AbstractBaseService<UserComment> implements UserCommentService {

    @Resource
    UserCommentMapper mapper;

    @Override
    protected IBaseMapper<UserComment> getMapper() {
        return this.mapper;
    }

    @Override
    public ServerResponse insertUserCommentService(UserComment userComment) {
        userComment.setCreatedAt(new Date());
        userComment.setStatus(1);//1 正常 2 审核不通过 -1 删除',
        int i = mapper.insertSelective(userComment);
        if(i>0)
            return ServerResponse.createBySuccess();
        else
            return ServerResponse.createByError();
    }

    @Override
    public List<Map<String, Object>> seartchUserCommentListByArticleId(Integer articleId) {
        List<UserComment> lists =  mapper.seartchUserCommentListByArticleId(articleId);
        List<Map<String,Object>> comboTreeList  =new ArrayList<Map<String,Object>>();
        for(UserComment uc:lists){
            Map<String, Object> map = null;
            if (uc.getPid()==0 && uc.getReplyUid()==0){
                map = new HashMap<String, Object>();
                map.put("id", uc.getId());                                  //id
                map.put("pid",uc.getPid());                                 //资源名
                map.put("userName",uc.getUser().getUserName());             //用户名
                map.put("content", uc.getContent());                        //回复内容
                map.put("replyUid",uc.getReplyUid());                       //回复目标ID
                map.put("createdAt",uc.getCreatedAt());                     //创建时间
                map.put("children", createComboTreeChildren(lists,uc.getId()));
            }
            if (map!= null){
                comboTreeList.add(map);
            }
        }
        return comboTreeList;
    }

    @Override
    public PageInfo seartchUserCommentListByContent(int pageNum, int pageSize, String content) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserComment> userComments = mapper.seartchUserCommentListByContent(content);
        PageInfo pageResult = new PageInfo(userComments);
        return pageResult;
    }

    /**
     * 递归设置查询回复内容
     * @param lists
     * @param id
     * @return
     */
    private List<Map<String,Object>> createComboTreeChildren(List<UserComment> lists, Integer id) {
        List<Map<String,Object>> childList = new ArrayList<Map<String,Object>>();
        for (UserComment uc:lists) {
            Map<String,Object> map = null;
            if (uc.getPid()==id){
                map = new HashMap<String,Object>();
                map.put("id", uc.getId());                              //id
                map.put("pid",uc.getPid());                             //资源名
                map.put("userName",uc.getUser().getUserName());         //用户名
                map.put("content", uc.getContent());                    //回复内容
                map.put("replyUid",uc.getReplyUid());                   //回复目标ID
                map.put("createdAt",uc.getCreatedAt());                 //创建时间
                map.put("children", createComboTreeChildrens(lists, uc.getId()));
            }
            if (map != null){
                childList.add(map);
            }
        }
        return childList;
    }
    /**
     * 递归开始聊天
     * @param lists
     * @param id
     * @return
     */
    private List<Map<String,Object>> createComboTreeChildrens(List<UserComment> lists, Integer id) {
        List<Map<String,Object>> childList = new ArrayList<Map<String,Object>>();
        for (UserComment uc:lists) {
            Map<String,Object> map = null;
            if (uc.getReplyUid()==id){
                map = new HashMap<String,Object>();
                map.put("id", uc.getId());                              //id
                map.put("pid",uc.getPid());                             //资源名
                map.put("userName",uc.getUser().getUserName());         //用户名
                map.put("content", uc.getContent());                    //回复内容
                map.put("replyUid",uc.getReplyUid());                   //回复目标ID
                map.put("createdAt",uc.getCreatedAt());                 //创建时间
                map.put("children", createComboTreeChildrens(lists, uc.getId()));
            }
            if (map != null){
                childList.add(map);
            }
        }
        return childList;
    }
}
