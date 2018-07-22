package top.atstudy.component.auth.service;

import org.springframework.stereotype.Service;
import top.atstudy.component.auth.vo.AdminLoginReq;
import top.atstudy.component.auth.vo.AppLoginReq;
import top.atstudy.component.base.config.AuthToken;
import top.atstudy.component.base.config.Constants;
import top.atstudy.component.base.util.RandUtil;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-12
 * Time: 20:04
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthToken createToken(AdminLoginReq loginReq) {
        return createToken(loginReq.getUserId(), loginReq.getUserName());
    }

    @Override
    public AuthToken createToken(AppLoginReq loginReq) {
        return createToken(loginReq.getUserId(), loginReq.getUserName());
    }

    @Override
    public AuthToken createToken(Long userId, String userName) {
        long age = Constants.AUTH_TOKEN_AGE_MAX;
        // set authToken cookie
        final long now = System.currentTimeMillis();
        final long expiry = now + age * 1000;
        return new AuthToken(userId, userName, now, expiry, RandUtil.rand());
    }

}
