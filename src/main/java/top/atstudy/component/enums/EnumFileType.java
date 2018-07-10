package top.atstudy.component.enums;

import top.atstudy.component.enums.base.ILabelCodeEnum;

public enum EnumFileType implements ILabelCodeEnum<EnumFileType, String> {
    FILE("FILE", "文件"),
    IMAGE("IMAGE", "图片");

    private String code;
    private String label;

    EnumFileType(String code, String label) {
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
    public EnumFileType codeOf(String s) {
        return valueOf(s);
    }
}
