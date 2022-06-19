package com.metadata.train.common;

import lombok.Getter;

@Getter
public enum StudentRegistryApproveEnum {
    WAIT_APPROVE("1", "待审核"),
    AGREE("2", "审核通过"),
    DISAGREE("3", "审核未通过");

    private String code;
    private String message;

    StudentRegistryApproveEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getValueByCode(Integer code) {
        for (StudentRegistryApproveEnum studentRegistryApproveEnum : StudentRegistryApproveEnum.values()) {
            if (code.equals(studentRegistryApproveEnum.getCode())) {
                return studentRegistryApproveEnum.getMessage();
            }
        }
        return null;
    }

}
