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

    PARAMS_INVALID(4000101, "请求参数不合法"),
    QR_CODE_CREATE_FAIL(4000201, "二维码创建失败"),
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
