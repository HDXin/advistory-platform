package top.atstudy.component.base.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.atstudy.advistory.base.enums.BadRequest;
import top.atstudy.component.base.config.AuthToken;
import top.atstudy.component.base.config.Constants;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.exception.FrameworkException;
import top.atstudy.component.user.SessionUser;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-12
 * Time: 20:08
 */
public abstract class BasicController {

    protected String buildAuthToken(HttpServletResponse response, AuthToken authToken) throws FrameworkException {
        if(null != authToken && authToken.token() != null){
            setCookies(response, authToken);
            return authToken.token();
        }else{
            throw new APIException(BadRequest.USER_LOGIN_AUTH_FAILED);
        }
    }

    protected void setCookies(HttpServletResponse response, AuthToken authToken) {
        Cookie cookie = new Cookie(Constants.AUTH_TOKEN_NAME, authToken.token());
        cookie.setMaxAge(Constants.AUTH_TOKEN_AGE_MAX);
        cookie.setSecure(false);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    protected void clearCookies(HttpServletResponse response){
        Cookie cookie = new Cookie(Constants.AUTH_TOKEN_NAME, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    protected SessionUser getSessionUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SessionUser sessionUser = (SessionUser) request.getAttribute(Constants.SESSION_USER_KEY);
        if(sessionUser == null || sessionUser.getUserId() == null){
            sessionUser = new SessionUser();
            sessionUser.setUserId(-99L);
            sessionUser.setUserName("test");
        }

        return sessionUser;
    }
}
