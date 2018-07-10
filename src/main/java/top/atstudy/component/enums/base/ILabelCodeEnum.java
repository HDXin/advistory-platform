package top.atstudy.component.enums.base;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-10
 * Time: 3:34
 */
public interface ILabelCodeEnum<T extends Enum<T>, C> extends ICodeEnum<T, C> {
    String getLabel();
}