package top.atstudy.component.auth.service;

import top.atstudy.component.auth.vo.AdminLoginReq;
import top.atstudy.component.auth.vo.AppLoginReq;
import top.atstudy.component.base.config.AuthToken;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-12
 * Time: 20:04
 */
public interface AuthService {

    AuthToken createToken(AdminLoginReq loginReq);

    AuthToken createToken(AppLoginReq loginReq);

    AuthToken createToken(Long userId, String userName);

}
