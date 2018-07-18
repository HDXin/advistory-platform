package top.atstudy.component.enums;

import top.atstudy.component.enums.base.ILabelCodeEnum;

public enum EnumSwiperType implements ILabelCodeEnum<EnumSwiperType, String> {

    AD("AD", "广告"),

    ;

    private String code;
    private String label;

    EnumSwiperType(String code, String label) {
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
    public EnumSwiperType codeOf(String s) {
        return valueOf(s);
    }
}
