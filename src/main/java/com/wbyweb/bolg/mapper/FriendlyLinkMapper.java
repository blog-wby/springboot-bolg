package com.wbyweb.bolg.mapper;

import com.wbyweb.bolg.po.FriendlyLink;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 友情链接
 */
public interface FriendlyLinkMapper  extends IBaseMapper<FriendlyLink>{

    List<FriendlyLink> searchFriendlyLinkBylinkName(@Param("linkName")String linkName);

}