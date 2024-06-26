package com.my.bbs.service;

import com.my.bbs.entity.BBSPost;
import com.my.bbs.util.PageQueryUtil;
import com.my.bbs.util.PageResult;

import java.util.List;

public interface BBSPostService {

    int savePost(BBSPost bbsPost);

    BBSPost getBBSPostById(Long bbsPostId);

    BBSPost getBBSPostForDetail(Long bbsPostId);

    int updateBBSPost(BBSPost bbsPost);

    int delBBSPost(Long userId, Long postId);

    PageResult getBBSPostPageForIndex(PageQueryUtil pageUtil);

    public PageResult getBBSPostPageForAdmin(PageQueryUtil pageUtil);

    List getHotTopicBBSPostList();

    List getNewTopicBBSPostList();

    List<BBSPost> getMyBBSPostList(Long userId);

    List<BBSPost> getRecentPostListByUserId(Long userId);

    Boolean approvePost(Long postId);

    Boolean banPost(Long postId);
}
