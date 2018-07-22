package top.atstudy.component.enums;


import top.atstudy.component.enums.base.ILabelCodeEnum;

public enum EnumUserType implements ILabelCodeEnum<EnumUserType, String> {
    LEVEL_A("LEVEL_A", "A类用户"),
    LEVEL_B("LEVEL_B", "A类用户"),
    LEVEL_C("LEVEL_C", "A类用户"),

    ;

    private String code;
    private String label;

    EnumUserType(String code, String label) {
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
    public EnumUserType codeOf(String s) {
        return valueOf(s);
    }
}
