package top.atstudy.component.enums;

import top.atstudy.component.enums.base.ILabelCodeEnum;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-16
 * Time: 21:25
 */
public enum EnumDeleted implements ILabelCodeEnum<EnumDeleted, Integer> {
    NORMAL(Integer.valueOf(0), "正常"),
    DELETED(Integer.valueOf(1), "已删除");

    private Integer code;
    private String label;

    private EnumDeleted(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public Integer getCode() {
        return this.code;
    }

    public EnumDeleted codeOf(Integer code) {
        return (EnumDeleted) Arrays.stream(values()).filter((v) -> {
            return v.getCode().equals(code);
        }).findFirst().get();
    }
}
