package top.atstudy.advistory.base.enums;

import top.atstudy.component.enums.http.IError401Enum;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-10
 * Time: 4:38
 */
public enum Unauthorized implements IError401Enum<Unauthorized> {
    USER_LOGIN_AUTH_FAILED(4010101, "登陆授权失败，账号或密码错误"),


    ;

    private Integer code;
    private String message;
    Unauthorized(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

}
