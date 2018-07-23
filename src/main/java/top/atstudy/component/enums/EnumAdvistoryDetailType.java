package top.atstudy.component.enums;

import top.atstudy.component.enums.base.ILabelCodeEnum;

public enum EnumAdvistoryDetailType implements ILabelCodeEnum<EnumAdvistoryDetailType, String> {

    IMAGE("IMAGE", "图片"),
    TEXT("TEXT", "文本"),

    ;

    private String code;
    private String label;

    EnumAdvistoryDetailType(String code, String label) {
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
    public EnumAdvistoryDetailType codeOf(String s) {
        return valueOf(s);
    }
}
