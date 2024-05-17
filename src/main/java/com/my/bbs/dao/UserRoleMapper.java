package com.my.bbs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.bbs.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色关联表mapper接口
 *
 * @author dinghao
 * @date 2021/3/12
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 根据用户id查询角色code码集合
     *
     * @param userId 用户id
     * @return
     */
    List<String> selectRoleByUserId(@Param("userId") Long userId);
}