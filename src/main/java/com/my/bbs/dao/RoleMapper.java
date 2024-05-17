package com.my.bbs.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.bbs.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表mapper接口
 *
 * @author dinghao
 * @date 2021/3/12
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
