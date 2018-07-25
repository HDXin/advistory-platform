package top.atstudy.component.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import top.atstudy.advistory.base.enums.http.Unauthorized;
import top.atstudy.component.base.config.AuthToken;
import top.atstudy.component.base.config.Constants;
import top.atstudy.component.base.config.SelfConfig;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.user.AdminSessionUser;
import top.atstudy.component.user.AppSessionUser;
import top.atstudy.component.user.service.IAdminUserService;
import top.atstudy.component.user.service.IAppUserService;
import top.atstudy.component.user.vo.resp.AdminUserResp;
import top.atstudy.component.user.vo.resp.AppUserResp;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-09
 * Time: 17:21
 */
public class AuthInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private SelfConfig selfConfig;

    @Autowired
    private IAdminUserService adminUserService;

    @Autowired
    private IAppUserService appUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info(" ===>> auth preHandle ...");
        if(checkAuth(request, response))
            return true;

        throw new APIException(Unauthorized.UNAUTHORIZED);
    }

    private boolean checkAuth(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get token string from cookie
        String tokenString = getTokenString(request);

        if (tokenString == null) {
            logger.warn("Not found authToken in cookie");
            return false;
        }

        // parse AuthToken
        AuthToken authToken = null;
        try {
            authToken = AuthToken.parse(tokenString);
        } catch (Exception e) {
            logger.error("Failed decrypt token: {}, exception: {}", tokenString, e.getMessage());
            clearAuthTokenCookie(response, request);
            return false;
        }

        if (AuthToken.isActive(authToken)) {
            // place user info
            if(authToken.userName.startsWith("WX-")){
                AppUserResp appUserResp = appUserService.getById(authToken.userId);
                AppSessionUser appSessionUser = BeanUtils.copyProperties(appUserResp, AppSessionUser.class);
                request.setAttribute(Constants.SESSION_USER_KEY, appSessionUser);
            }else{
                AdminUserResp adminUserResp = adminUserService.getById(authToken.userId);
                AdminSessionUser adminSessionUser = BeanUtils.copyProperties(adminUserResp, AdminSessionUser.class);
                request.setAttribute(Constants.SESSION_USER_KEY, adminSessionUser);
            }
            return true;
        }

        logger.warn("authToken cookie expired: {}={}", Constants.AUTH_TOKEN_NAME, tokenString);
        clearAuthTokenCookie(response, request);
        return false;
    }

    protected String getTokenString(HttpServletRequest httpRequest) {
        // from querystring
        String tokenString = httpRequest.getParameter(Constants.AUTH_TOKEN_NAME_DEFAULT);

        // from header
        if (StringUtils.isBlank(tokenString)) {
            tokenString = httpRequest.getHeader(Constants.AUTH_TOKEN_NAME_DEFAULT);
        }

        // from cookie
        if (httpRequest.getCookies() != null) {
            for (Cookie c : httpRequest.getCookies()) {
                if (Constants.AUTH_TOKEN_NAME.equals(c.getName())) {
                    tokenString = c.getValue();
                    break;
                }
                if (Constants.AUTH_TOKEN_NAME_DEFAULT.equals(c.getName())) {
                    tokenString = c.getValue();
                    break;
                }
            }
        }

        return tokenString;
    }

    private void clearAuthTokenCookie(HttpServletResponse response, HttpServletRequest request) {
        // clear cookie
        Cookie cookie = new Cookie(Constants.AUTH_TOKEN_NAME, null);
        cookie.setDomain(getCookieDomain(request));
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    private String getCookieDomain(HttpServletRequest request){
        String domain = selfConfig.getCookieDomain();
        if(StringUtils.isBlank(domain)){
            domain = request.getServerName();
        }
        return domain;
    }
}
