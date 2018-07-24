package top.atstudy.component.enums;


import top.atstudy.component.enums.base.ILabelCodeEnum;

public enum EnumAdvistoryLevel implements ILabelCodeEnum<EnumAdvistoryLevel, String> {
    LEVEL_A("LEVEL_A", "A类资讯"),
    LEVEL_B("LEVEL_B", "B类资讯"),
    LEVEL_C("LEVEL_C", "C类资讯"),

    ;

    private String code;
    private String label;

    EnumAdvistoryLevel(String code, String label) {
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
    public EnumAdvistoryLevel codeOf(String s) {
        return valueOf(s);
    }
}
