package top.atstudy.component.enums;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-06-20
 * Time: 17:57
 */
public enum EnumRequestMethod {

    GET("GET"),
    POST("POST"),

    ;

    private String code;
    EnumRequestMethod(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
