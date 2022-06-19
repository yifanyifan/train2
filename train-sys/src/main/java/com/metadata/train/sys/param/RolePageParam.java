package com.metadata.train.sys.param;

import com.metadata.train.entity.common.BasePageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 *  分页参数对象
 * </pre>
 *
 * @author 易樊
 * @date 2022-04-07
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "分页参数")
public class RolePageParam extends BasePageParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    private Long id;
}
