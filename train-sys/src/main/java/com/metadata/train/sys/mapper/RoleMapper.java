package com.metadata.train.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.metadata.train.entity.sys.Role;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoleByUser(@Param("param") Long id);
}
