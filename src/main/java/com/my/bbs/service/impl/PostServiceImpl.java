package com.my.bbs.service.impl;

import com.my.bbs.dao.CategoryMapper;
import com.my.bbs.dao.PostMapper;
import com.my.bbs.dao.UserMapper;
import com.my.bbs.entity.*;
import com.my.bbs.service.PostService;
import com.my.bbs.util.BeanUtil;
import com.my.bbs.util.PageQueryUtil;
import com.my.bbs.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public int savePost(Post post) {
        User User = userMapper.selectByPrimaryKey(post.getPublishUserId());
        if (User == null || User.getUserStatus().intValue() == 1) {
            //账号已被封禁
            return 0;
        }

        Category category = categoryMapper.selectByPrimaryKey(post.getPostCategoryId());
        if (category == null) {
            //分类数据错误
            return 0;
        }
        return postMapper.insertSelective(post);
    }

    @Override
    public Post getBBSPostById(Long bbsPostId) {
        return postMapper.selectByPrimaryKey(bbsPostId);
    }

    @Override
    public Post getBBSPostForDetail(Long bbsPostId) {
        Post bbsPost = postMapper.selectByPrimaryKey(bbsPostId);
        if (bbsPost != null) {
            bbsPost.setPostViews(bbsPost.getPostViews() + 1);
            postMapper.updateByPrimaryKeySelective(bbsPost);
        }
        return bbsPost;
    }

    @Override
    public int updateBBSPost(Post bbsPost) {
        User user = userMapper.selectByPrimaryKey(bbsPost.getPublishUserId());

        if (user == null || user.getUserStatus().intValue() == 1) {
            //账号已被封禁
            return 0;
        }

        Category category = categoryMapper.selectByPrimaryKey(bbsPost.getPostCategoryId());
        if (category == null) {
            //分类数据错误
            return 0;
        }

        return postMapper.updateByPrimaryKeySelective(bbsPost);
    }

    @Override
    public int delBBSPost(Long userId, Long postId) {
        User user = userMapper.selectByPrimaryKey(userId);

        if (user == null || user.getUserStatus().intValue() == 1) {
            //账号已被封禁
            return 0;
        }

        Post bbsPost = postMapper.selectByPrimaryKey(postId);
        // 对象不为空且发帖人为当前登录用户才可以删除
        if (bbsPost != null && bbsPost.getPublishUserId().equals(userId)) {
            return postMapper.deleteByPrimaryKey(postId);
        }
        return 0;
    }

    @Override
    public PageResult getBBSPostPageForIndex(PageQueryUtil pageUtil) {
        //查询帖子数据
        int total = postMapper.getTotalBBSPosts(pageUtil);
        List<Post> bbsPostList = postMapper.findBBSPostList(pageUtil);
        List<PostListEntity> bbsPostListEntities = new ArrayList<>();
        //数据格式转换
        if (!CollectionUtils.isEmpty(bbsPostList)) {
            bbsPostListEntities = BeanUtil.copyList(bbsPostList, PostListEntity.class);
            List<Long> userIds = bbsPostListEntities.stream().map(PostListEntity::getPublishUserId).collect(Collectors.toList());
            //查询user数据
            List<User> bbsUsers = userMapper.selectByPrimaryKeys(userIds);
            if (!CollectionUtils.isEmpty(bbsUsers)) {
                //封装user数据
                Map<Long, User> bbsUserMap = bbsUsers.stream().collect(Collectors.toMap(User::getId, Function.identity(), (entity1, entity2) -> entity1));
                for (PostListEntity bbsPostListEntity : bbsPostListEntities) {
                    if (bbsUserMap.containsKey(bbsPostListEntity.getPublishUserId())) {
                        //设置头像字段和昵称字段
                        User tempUser = bbsUserMap.get(bbsPostListEntity.getPublishUserId());
                        bbsPostListEntity.setHeadImgUrl(tempUser.getAvatar());
                        bbsPostListEntity.setNickName(tempUser.getNickname());
                    }
                }
            }
        }
        PageResult pageResult = new PageResult(bbsPostListEntities, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public List getHotTopicBBSPostList() {
        List<Post> bbsPostList = postMapper.getHotTopicBBSPostList();
        List<HotTopicBBSPostListEntity> hotTopicBBSPostList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(bbsPostList)) {
            hotTopicBBSPostList = BeanUtil.copyList(bbsPostList, HotTopicBBSPostListEntity.class);
        }
        return hotTopicBBSPostList;
    }

    @Override
    public List<Post> getMyBBSPostList(Long userId) {
        return postMapper.getMyBBSPostList(userId, "all");
    }

    @Override
    public List<Post> getRecentPostListByUserId(Long userId) {
        return postMapper.getMyBBSPostList(userId, "recent");
    }
}
