package com.metadata.train.entity.sys;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.metadata.train.validator.groups.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 易樊
 * @since 2022-04-07
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("db_user")
@ApiModel(value = "User对象")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "id不能为空", groups = {Update.class})
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "登录名不能为空")
    @ApiModelProperty("登录名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("编号（学号或身份证号）")
    private String code;

    @ApiModelProperty("联系电话")
    private String contact;

    @ApiModelProperty("等级：1:初级；2：中级；3：高级")
    private Integer level;

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

    @ApiModelProperty("备注说明")
    private String remark;

    @ApiModelProperty("用户类型")
    private Integer userType;

    @ApiModelProperty("年龄")
    private String age;

    @ApiModelProperty("类型,(本校、非本校)")
    private String category;

    @ApiModelProperty("班级id")
    private Long classId;

    @ApiModelProperty("班级code")
    private String classCode;

    @ApiModelProperty("班级名称")
    private String className;

    @ApiModelProperty("住址")
    private String address;

    @ApiModelProperty("学科")
    private String subject;

    @ApiModelProperty("简介")
    private String teacherAb;

    @ApiModelProperty("院系")
    private String unit;

    @ApiModelProperty("照片地址")
    private String photo;

    @ApiModelProperty("最后登录时间")
    private Date lastLoginTime;

    @ApiModelProperty("是否启用")
    private Boolean isEnabled;

    @ApiModelProperty("创建人")
    private Long createBy;

    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新人")
    private Long updateBy;

    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("是否删除")
    private Integer delFlag;

    @ApiModelProperty("区分你是要走admin的用户认证还是member的用户认证嘛")
    @TableField(exist = false)
    private String clientId;

    @ApiModelProperty("角色集合")
    @TableField(exist = false)
    private List<Role> roleList;

    @ApiModelProperty("角色集合-临时")
    @TableField(exist = false)
    private List<String> roleListStr;

    @ApiModelProperty("权限集合")
    @TableField(exist = false)
    private List<String> permissionList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", userType=" + userType +
                ", level=" + level +
                ", age='" + age + '\'' +
                ", category='" + category + '\'' +
                ", classId=" + classId +
                ", classCode='" + classCode + '\'' +
                ", className='" + className + '\'' +
                ", duty='" + duty + '\'' +
                ", education='" + education + '\'' +
                ", militaryType='" + militaryType + '\'' +
                ", origin='" + origin + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", subject='" + subject + '\'' +
                ", teacherAb='" + teacherAb + '\'' +
                ", unit='" + unit + '\'' +
                ", contact='" + contact + '\'' +
                ", photo='" + photo + '\'' +
                ", remark='" + remark + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", isEnabled=" + isEnabled +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", delFlag=" + delFlag +
                '}';
    }
}
