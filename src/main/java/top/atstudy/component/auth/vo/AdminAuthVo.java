package top.atstudy.component.auth.vo;

import top.atstudy.component.user.vo.resp.AdminUserResp;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-12
 * Time: 19:58
 */
public class AdminAuthVo extends AdminUserResp{

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
