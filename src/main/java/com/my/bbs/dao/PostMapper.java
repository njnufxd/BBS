package com.my.bbs.dao;

import com.my.bbs.entity.Post;
import com.my.bbs.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    int deleteByPrimaryKey(Long postId);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Long postId);

    List<Post> selectByPrimaryKeys(@Param("postIds")List<Long> postIds);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKeyWithBLOBs(Post record);

    int updateByPrimaryKey(Post record);

    int getTotalBBSPosts(PageQueryUtil pageUtil);

    List<Post> findBBSPostList(PageQueryUtil pageUtil);

    List<Post> getHotTopicBBSPostList();

    List<Post> getMyBBSPostList(@Param("userId") Long userId,@Param("period") String period);
}