package com.my.bbs.service;

import com.my.bbs.entity.Post;
import com.my.bbs.util.PageQueryUtil;
import com.my.bbs.util.PageResult;

import java.util.List;

public interface PostService {

    /**
     * 保存帖子
     *
     * @param bbsPost
     * @return
     */
    int savePost(Post bbsPost);

    /**
     * 获取详情
     *
     * @param bbsPostId
     * @return
     */
    Post getBBSPostById(Long bbsPostId);

    /**
     * 获取详情&浏览数加1
     *
     * @param bbsPostId
     * @return
     */
    Post getBBSPostForDetail(Long bbsPostId);

    /**
     * 修改帖子
     *
     * @param bbsPost
     * @return
     */
    int updateBBSPost(Post bbsPost);

    /**
     * 删除帖子
     *
     * @param userId
     * @param postId
     * @return
     */
    int delBBSPost(Long userId, Long postId);

    /**
     * 首页帖子列表
     *
     * @param pageUtil
     * @return
     */
    PageResult getBBSPostPageForIndex(PageQueryUtil pageUtil);

    /**
     * 近期热议帖子列表
     *
     * @return
     */
    List getHotTopicBBSPostList();

    /**
     * 根据userId查询发布的所有帖子
     *
     * @return
     */
    List<Post> getMyBBSPostList(Long userId);

    /**
     * 根据userId获取最近发帖列表
     *
     * @param userId
     * @return
     */
    List<Post> getRecentPostListByUserId(Long userId);
}
