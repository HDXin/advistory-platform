package top.atstudy.component.enums.base;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-10
 * Time: 3:33
 */
public interface ICodeEnum<T extends Enum<T>, C> {
    C getCode();

    T codeOf(C var1);

    static ICodeEnum codeOf(Enum instance, Object code) {
        ICodeEnum sub = (ICodeEnum)instance;
        return (ICodeEnum)sub.codeOf(code);
    }
}

