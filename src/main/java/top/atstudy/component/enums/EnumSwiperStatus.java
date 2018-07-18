package top.atstudy.component.enums;

import top.atstudy.component.enums.base.ILabelCodeEnum;

public enum EnumSwiperStatus implements ILabelCodeEnum<EnumSwiperStatus, String> {

    ENABLE("ENABLE", "启用"),
    DISABLE("DISABLE", "禁用"),

    ;

    private String code;
    private String label;

    EnumSwiperStatus(String code, String label) {
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
    public EnumSwiperStatus codeOf(String s) {
        return valueOf(s);
    }
}
