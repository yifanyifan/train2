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
 * 学员注册表
 *
 * @author 易樊
 * @since 2022-04-07
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("db_student_registry")
@ApiModel(value = "StudentRegistry对象")
public class StudentRegistry implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("证件号")
    private String code;

    @ApiModelProperty("手机号")
    private String phoneNum;

    @ApiModelProperty("级别，初级、中级、高级")
    private String level;

    @ApiModelProperty("学历")
    private String education;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("岗位")
    private String duty;

    @ApiModelProperty("籍贯")
    private String origin;

    @ApiModelProperty("军兵种")
    private String militaryType;

    @ApiModelProperty("注册状态，1：待审核；2：审通过；3：审核未通过")
    private String approveStatus;

    @ApiModelProperty("备注说明")
    private String remark;

    private Long createBy;

    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer delFlag;

    private Long updateBy;

    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
