package com.metadata.train.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.metadata.train.entity.sys.Permission;
import com.metadata.train.entity.sys.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *  Mapper 接口
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getPermissionByRole(@Param("params") List<Role> roleList);

    List<Map<String, String>> getUrlAndPermissionAll();

    List<Permission> getMenuList(@Param("param") Long id);
}
