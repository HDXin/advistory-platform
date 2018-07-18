package top.atstudy.component.base.vo;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-18
 * Time: 10:36
 */
public class ErrorVo {

    private Integer code;
    private String message;
    private String path;
    private Date datetime;

    public ErrorVo() {
        this.datetime = new Date();
    }
    public ErrorVo(Integer code, String message, String path) {
        this.code = code;
        this.message = message;
        this.path = path;
        this.datetime = new Date();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
