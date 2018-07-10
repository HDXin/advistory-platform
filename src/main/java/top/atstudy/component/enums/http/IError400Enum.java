package top.atstudy.component.enums.http;

import top.atstudy.component.enums.base.IErrorEnum;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-10
 * Time: 3:40
 */
public interface IError400Enum<T extends Enum<T>> extends IErrorEnum<T> {
    default Integer configHttpCode() {
        return Integer.valueOf(400);
    }

    default String configReason() {
        return "[Bad Request] - 请求参数不合法";
    }
}
