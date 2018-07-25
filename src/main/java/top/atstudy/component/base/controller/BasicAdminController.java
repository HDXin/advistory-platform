package top.atstudy.component.base.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.atstudy.component.base.config.Constants;
import top.atstudy.component.user.AdminSessionUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-25
 * Time: 11:04
 */
public abstract class BasicAdminController extends BasicController{

    protected AdminSessionUser getSessionUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        AdminSessionUser sessionUser = (AdminSessionUser) request.getAttribute(Constants.SESSION_USER_KEY);
        if(sessionUser == null || sessionUser.getUserId() == null){
            sessionUser = new AdminSessionUser();
            sessionUser.setUserId(-99L);
            sessionUser.setUserName("test");
        }

        return sessionUser;
    }
}
