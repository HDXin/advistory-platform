package top.atstudy.advistory.base.enums.http;

import top.atstudy.component.enums.http.IError403Enum;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-10
 * Time: 3:46
 */
public enum Forbidden implements IError403Enum<Forbidden> {

    SERVER_FORBIDDEN(4030101, "服务器拒绝执行");

    private Integer code;
    private String message;
    Forbidden(Integer code, String message) {
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
