package top.atstudy.component.enums;

import top.atstudy.component.enums.base.ILabelCodeEnum;

public enum EnumOrderStatus implements ILabelCodeEnum<EnumOrderStatus, String> {
    NEW_ORDER("NEW_ORDER", "新订单"),
    PREPAY("PREPAY", "预支付"),
    PAID("PAID", "已支付"),
    ;

    private String code;
    private String label;

    EnumOrderStatus(String code, String label) {
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
    public EnumOrderStatus codeOf(String s) {
        return valueOf(s);
    }
}
