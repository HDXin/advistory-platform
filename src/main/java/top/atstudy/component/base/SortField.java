package top.atstudy.component.base;

import top.atstudy.component.enums.EnumOrder;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-16
 * Time: 21:43
 */
public class SortField {
    private String field;
    private EnumOrder order;

    public SortField(String field, EnumOrder order) {
        this.field = field;
        this.order = order;
    }

    public String getDBColumn() {
        return convertFieldToDBColumn(this.field);
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public EnumOrder getOrder() {
        return this.order;
    }

    public void setOrder(EnumOrder order) {
        this.order = order;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SortField [field=").append(this.field).append(", order=").append(this.order).append("]");
        return builder.toString();
    }

    public static String convertFieldToDBColumn(String field) {
        if (field == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < field.toCharArray().length; ++i) {
                if (Character.isUpperCase(field.charAt(i))) {
                    sb.append("_").append(Character.toLowerCase(field.charAt(i)));
                } else {
                    sb.append(field.charAt(i));
                }
            }

            return sb.toString();
        }
    }
}
