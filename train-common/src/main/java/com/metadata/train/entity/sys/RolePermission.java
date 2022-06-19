package com.metadata.train.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.metadata.train.validator.groups.Update;

/**
 * 
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("db_role_permission")
@ApiModel(value = "RolePermission对象")
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "不能为空")
    private Long roleId;

    @NotNull(message = "不能为空")
    private Long permissionId;

}
