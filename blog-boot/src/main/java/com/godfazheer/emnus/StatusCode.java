package com.godfazheer.emnus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {

    SUCCESS(10000, "操作成功"),

    FAIL(10001, "操作失败"),
    TOKEN_INVAILD(40001,"token无效"),
    NOT_LOGIN(20001, "用户未登录"),
    AUTHORIZED(30001, "没有操作权限"),
    SYSTEM_ERROR(50001, "系统异常"),
    LOGIN_FAILED(60001,"登录失败！");

    private final Integer code;

    private final String desc;
}
