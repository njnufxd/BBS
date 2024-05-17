package com.my.bbs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.bbs.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {


    List<String> selectRoleByUserId(@Param("userId") Long userId);
}
