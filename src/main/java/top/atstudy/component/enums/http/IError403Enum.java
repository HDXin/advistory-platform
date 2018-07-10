package top.atstudy.component.enums.http;

import top.atstudy.component.enums.base.IErrorEnum;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-10
 * Time: 3:44
 */
public interface IError403Enum<T extends Enum<T>> extends IErrorEnum<T> {
    default Integer configHttpCode() {
        return Integer.valueOf(403);
    }

    default String configReason() {
        return "[Forbidden] - 服务器拒绝执行该请求";
    }
}
