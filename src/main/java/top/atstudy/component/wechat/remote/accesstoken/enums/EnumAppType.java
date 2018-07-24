package top.atstudy.component.wechat.remote.accesstoken.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-06-20
 * Time: 22:48
 */
public enum EnumAppType {

    CONTACTS("CONTACTS", "通讯录"),

    ;
    private String code;
    private String label;
    EnumAppType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
