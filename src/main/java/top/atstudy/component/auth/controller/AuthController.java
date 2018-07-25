package top.atstudy.component.auth.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.base.enums.http.BadRequest;
import top.atstudy.component.auth.service.AuthService;
import top.atstudy.component.auth.vo.AdminAuthVo;
import top.atstudy.component.auth.vo.AppAuthVo;
import top.atstudy.component.auth.vo.AdminLoginReq;
import top.atstudy.component.auth.vo.AppLoginReq;
import top.atstudy.component.base.config.AuthToken;
import top.atstudy.component.base.controller.BasicAdminController;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.base.util.crypt.PasswordCrypt;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.exception.FrameworkException;
import top.atstudy.component.user.dao.IAdminUserDao;
import top.atstudy.component.user.dao.dto.AdminUserDTO;
import top.atstudy.component.user.service.IAppUserService;
import top.atstudy.component.user.vo.resp.AppUserResp;
import top.atstudy.component.wechat.remote.user.service.IWechatMiniUserService;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-12
 * Time: 19:56
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController extends BasicAdminController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private static final String WECHAT_SESSION_USER_TYPE = "WX";

    @Autowired
    private AuthService authService;

    @Autowired
    private IAdminUserDao adminUserDao;

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private IWechatMiniUserService wechatMiniUserService;

    @PostMapping("/mini/login")
    public AppAuthVo miniLogin(HttpServletResponse response,
                               @RequestBody AppLoginReq appLoginReq) throws FrameworkException, IOException {

        //1.jscode 不能为空
        if(StringUtils.isBlank(appLoginReq.getJscode()))
            throw new APIException(BadRequest.APP_USER_JSCODE_IS_NULL);

        //2.查询出当前 openid 对应的用户
        AppAuthVo appAuthVo = wechatMiniUserService.getByJscode(appLoginReq.getJscode());

        //3.获取指定 openid 对应的用户信息
        AppUserResp appUserResp = this.appUserService.getByOpenid(appAuthVo.getOpenid(), getSessionUser());

        //4.创建 token
        appLoginReq.setUserId(appUserResp.getUserId());
        appLoginReq.setUserName(WECHAT_SESSION_USER_TYPE + "-" + appAuthVo.getOpenid());
        AuthToken authToken = authService.createToken(appLoginReq);
        String token = buildAuthToken(response, authToken);
        logger.info(" --->> token: {}", token);

        //5.封装返回结果
        AppAuthVo authVo = BeanUtils.copyProperties(appUserResp, AppAuthVo.class);
        authVo.setToken(token);
        return authVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/admin/login")
    public AdminAuthVo adminLogin(HttpServletResponse response, @RequestBody AdminLoginReq adminLoginReq) throws FrameworkException {

        //1.用户名不能为空
        if(StringUtils.isBlank(adminLoginReq.getUserName()))
            throw new APIException(BadRequest.ADMIN_USER_NAME_NULL);

        //2.密码不能为空
        if(StringUtils.isBlank(adminLoginReq.getPassword()))
            throw new APIException(BadRequest.ADMIN_USER_PASSWORD_NULL);

        //3.判断用户是否存在
        AdminUserDTO temp = adminUserDao.getByName(adminLoginReq.getUserName());
        if(temp == null || temp.getUserId() == null)
            throw new APIException(BadRequest.ADMIN_USER_NAME_NOT_EXISTS);

        //4.判断用户名或密码是否匹配
        if(!temp.getPassword().equals(PasswordCrypt.encrypt(adminLoginReq.getPassword())))
            throw new APIException(BadRequest.ADMIN_USER_NAME_OR_PASS_INVALID);

        //5.创建 token
        adminLoginReq.setUserId(temp.getUserId());
        AuthToken authToken = authService.createToken(adminLoginReq);
        String token = buildAuthToken(response, authToken);
        logger.info(" --->> token: {}", token);

        //6.封装返回结果
        AdminAuthVo authVo = BeanUtils.copyProperties(temp, AdminAuthVo.class);
        authVo.setToken(token);
        return authVo;
    }


    public static void main(String[] args) {

        String str = "yyOQqyiela6W%2Fz%2Fmq%2B75m3XoTDFr6VCb1OTPUdgkf88xXnOZYTJEOCl01zEGN1YXAU27RozSxK4qbx9k3wDmwMxRCpo31D0CZ30NeolXQHS0BuesGEZMh5hlIq%2FxnfHN95LC2likftfd2ay%2FGM1C%2BVwjP8FADLjckYG3xDQhIUw%3D";
        AuthToken authToken = AuthToken.parse(str);

        logger.info(" ==>> {}", authToken.userId);

    }


}
