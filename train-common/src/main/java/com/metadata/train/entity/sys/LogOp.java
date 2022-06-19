package com.metadata.train.entity.sys;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.metadata.train.validator.groups.Update;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 操作日志表
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("db_log_op")
@ApiModel(value = "LogOp对象")
public class LogOp implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "logId不能为空", groups = {Update.class})
    @ApiModelProperty("日志主键")
    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    @ApiModelProperty("请求URL")
    private String reqUrl;

    @ApiModelProperty("操作类型:view.浏览,new.新增,modify.编辑,del.删除,export.导出")
    private String logType;

    @ApiModelProperty("IP")
    private String logIp;

    @NotNull(message = "操作人不能为空")
    @ApiModelProperty("操作人")
    private Integer createdBy;

    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("操作时间")
    private Date createdAt;

}
