package top.atstudy.component.wechat.remote.accesstoken.resp;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-06-20
 * Time: 22:09
 */
public class AccessTokenVo {

    private Long expires_in;
    private String errmsg;
    private String access_token;
    private Integer errcode;

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "expires_in=" + expires_in +
                ", errmsg='" + errmsg + '\'' +
                ", access_token='" + access_token + '\'' +
                ", errcode=" + errcode +
                '}';
    }
}
