package com.gt.common.constant;

/**
 * @desc 错误码  3位错误码：系统性错误码  6位错误码：业务错误码
 *  3位错误码开头：1.登录模块
 *  6位错误码开头：0.消费模块 1.会见模块
 * @author zhukeyan
 * @date 2022/4/25
 */
public enum ReturnCode {
    /**
     * 登录模块
     */
    accountNotFound("100","账户名或者密码输入错误!"),
    accountLocked("101","账户被锁定，请联系管理员!"),
    accountPasswordExpired("102","密码过期，请联系管理员!"),
    accountExpired("103","账户过期，请联系管理员!"),
    accountDisabled("104","账户被禁用，请联系管理员!"),
    accountLoginFailure("105","登录失败！"),
    accountLoginOther("106","账号在另一台电脑登录，请重新登录"),
    userUnauthorized("107","用户未登录，无权访问！"),
    insufficientPermissions("108","权限不足,请联系管理员!"),
    ;

    private final String code;
    private final String msg;

    private ReturnCode(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
