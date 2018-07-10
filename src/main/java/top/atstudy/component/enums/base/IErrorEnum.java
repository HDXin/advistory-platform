package top.atstudy.component.enums.base;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-10
 * Time: 3:34
 */
public interface IErrorEnum<T extends Enum<T>> extends ICodeEnum<T, Integer> {
    String configReason();

    Integer configHttpCode();

    default String getReason() {
        return this.configReason();
    }

    default Integer getHttpCode() {
        return this.configHttpCode();
    }

    String getMessage();

    default T codeOf(Integer code) {
        return null;
    }
}

