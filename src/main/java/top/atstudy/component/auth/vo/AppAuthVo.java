package top.atstudy.component.auth.vo;

import top.atstudy.component.user.vo.resp.AppUserResp;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-23
 * Time: 0:20
 */
public class AppAuthVo extends AppUserResp{

    private String token;

    // 凭证有效时间，单位：秒
    private String sessionKey;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
