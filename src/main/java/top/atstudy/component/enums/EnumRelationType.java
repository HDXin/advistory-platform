package top.atstudy.component.enums;

import top.atstudy.component.enums.base.ILabelCodeEnum;

public enum EnumRelationType implements ILabelCodeEnum<EnumRelationType, String> {
    ADVISTORY("ADVISTORY", "资讯"),
    ;

    private String code;
    private String label;

    EnumRelationType(String code, String label) {
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
    public EnumRelationType codeOf(String s) {
        return valueOf(s);
    }
}
