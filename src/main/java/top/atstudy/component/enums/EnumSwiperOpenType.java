package top.atstudy.component.enums;

import top.atstudy.component.enums.base.ILabelCodeEnum;

public enum EnumSwiperOpenType implements ILabelCodeEnum<EnumSwiperOpenType, String> {

    NONE("NONE", "不需打开"),

    ;

    private String code;
    private String label;

    EnumSwiperOpenType(String code, String label) {
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
    public EnumSwiperOpenType codeOf(String s) {
        return valueOf(s);
    }
}
