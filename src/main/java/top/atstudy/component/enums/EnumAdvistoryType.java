package top.atstudy.component.enums;

import top.atstudy.component.enums.base.ILabelCodeEnum;

public enum EnumAdvistoryType implements ILabelCodeEnum<EnumAdvistoryType, String> {

    GENERAL("GENERAL", "常规"),

    ;

    private String code;
    private String label;

    EnumAdvistoryType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public EnumAdvistoryType codeOf(String s) {
        return valueOf(s);
    }
}
