package top.atstudy.component.enums;

import top.atstudy.component.enums.base.ILabelCodeEnum;

public enum EnumAdvistoryCategory implements ILabelCodeEnum<EnumAdvistoryCategory, String> {

    GENERAL("GENERAL", "常规"),

    ;

    private String code;
    private String label;

    EnumAdvistoryCategory(String code, String label) {
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
    public EnumAdvistoryCategory codeOf(String s) {
        return valueOf(s);
    }
}
