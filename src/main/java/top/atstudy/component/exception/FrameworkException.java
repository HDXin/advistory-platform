package top.atstudy.component.exception;

import top.atstudy.component.enums.base.IErrorEnum;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-10
 * Time: 3:48
 */
public class FrameworkException extends Exception{
    protected IErrorEnum errorEnum;

    public FrameworkException(IErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public IErrorEnum getErrorEnum() {
        return this.errorEnum;
    }

    public FrameworkException setErrorEnum(IErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
        return this;
    }

    public String toString() {
        return "FrameworkException{errorEnum=" + this.errorEnum + '}';
    }
}
