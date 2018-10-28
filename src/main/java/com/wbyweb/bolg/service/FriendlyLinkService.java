package com.wbyweb.bolg.service;

import com.github.pagehelper.PageInfo;
import com.wbyweb.bolg.common.ServerResponse;
import com.wbyweb.bolg.po.FriendlyLink;

public interface FriendlyLinkService {

    PageInfo searchFriendlyLinkBylinkName(int pageNum, int pageSize, String linkName);

    ServerResponse insertFriendlyLink(FriendlyLink friendlyLink);

    ServerResponse delFriendlyLinkById(Integer linkId);

    ServerResponse searchFriendlyLinkBylinkId(Integer linkId);
}
