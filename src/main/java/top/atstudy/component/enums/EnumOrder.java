package top.atstudy.component.enums;

import top.atstudy.component.enums.base.ILabelCodeEnum;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-16
 * Time: 21:41
 */
public enum EnumOrder implements ILabelCodeEnum<EnumOrder, String> {
    ASC("ASC", "正序"),
    DESC("DESC", "倒序");

    private String code;
    private String label;

    private EnumOrder(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public String getCode() {
        return this.code;
    }

    public EnumOrder codeOf(String s) {
        return valueOf(s);
    }
}
