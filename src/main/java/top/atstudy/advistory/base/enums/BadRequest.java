package top.atstudy.advistory.base.enums;

import top.atstudy.component.enums.http.IError400Enum;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-10
 * Time: 3:38
 */
public enum BadRequest implements IError400Enum<BadRequest> {

    USER_LOGIN_AUTH_FAILED(4010101, "登陆授权失败，账号或密码错误"),
    PARAMS_INVALID(4000201, "请求参数不合法"),
    QR_CODE_CREATE_FAIL(4000301, "二维码创建失败"),

    ADMIN_USER_ID_INVALID(4000401, "userId 无效"),
    ADMIN_USER_OLD_PASS_INVALID(4000402, "输入密码错误"),
    ADMIN_USER_NAME_NULL(4000403, "用户名不能为空"),
    ADMIN_USER_NAME_EXISTS(4000504, "用户名已存在"),

    ;

    private Integer code;
    private String message;
    BadRequest(Integer code, String message) {
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
