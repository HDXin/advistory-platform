package top.atstudy.component.enums.http;

import top.atstudy.component.enums.base.IErrorEnum;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-10
 * Time: 3:43
 */
public interface IError401Enum<T extends Enum<T>> extends IErrorEnum<T> {
    default Integer configHttpCode() {
        return Integer.valueOf(401);
    }

    default String configReason() {
        return "[Unauthorized] - 当前请求未通过授权";
    }
}
