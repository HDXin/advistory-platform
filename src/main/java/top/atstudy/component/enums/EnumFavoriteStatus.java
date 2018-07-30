package top.atstudy.component.enums;

import top.atstudy.component.enums.base.ILabelCodeEnum;

public enum EnumFavoriteStatus implements ILabelCodeEnum<EnumFavoriteStatus, String> {
    ADD_FAVORITE("ADD_FAVORITE", "添加收藏"),
    CANCEL_FAVORITE("CANCEL_FAVORITE", "取消收藏"),
    ;

    private String code;
    private String label;

    EnumFavoriteStatus(String code, String label) {
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
    public EnumFavoriteStatus codeOf(String s) {
        return valueOf(s);
    }
}
