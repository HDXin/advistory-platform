package top.atstudy.component.exception;

import top.atstudy.component.enums.base.IErrorEnum;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-09
 * Time: 23:01
 */
public class APIException extends FrameworkException{
    public APIException(IErrorEnum errorEnum) {
        super(errorEnum);
    }

    public String toString() {
        return "APIException{errorEnum=" + super.errorEnum + '}';
    }
}
