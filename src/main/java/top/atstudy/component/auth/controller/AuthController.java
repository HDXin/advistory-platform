package top.atstudy.component.auth.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.base.enums.BadRequest;
import top.atstudy.component.auth.service.AuthService;
import top.atstudy.component.auth.vo.AuthVo;
import top.atstudy.component.auth.vo.LoginReq;
import top.atstudy.component.base.config.AuthToken;
import top.atstudy.component.base.controller.BasicController;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.base.util.crypt.PasswordCrypt;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.exception.FrameworkException;
import top.atstudy.component.user.dao.IAdminUserDao;
import top.atstudy.component.user.dao.dto.AdminUserDTO;

import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-12
 * Time: 19:56
 */
@RestController
@RequestMapping("/auth")
public class AuthController extends BasicController{
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private IAdminUserDao adminUserDao;

    @PostMapping("/mini/login")
    public String miniLogin(@RequestBody AuthVo authVo){

//        AuthToken authToken = new AuthToken();


        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/admin/login")
    public AuthVo adminLogin(HttpServletResponse response, @RequestBody LoginReq loginReq) throws FrameworkException {

        //1.用户名不能为空
        if(StringUtils.isBlank(loginReq.getUserName()))
            throw new APIException(BadRequest.ADMIN_USER_NAME_NULL);

        //2.密码不能为空
        if(StringUtils.isBlank(loginReq.getPassword()))
            throw new APIException(BadRequest.ADMIN_USER_PASSWORD_NULL);

        //3.判断用户是否存在
        AdminUserDTO temp = adminUserDao.getByName(loginReq.getUserName());
        if(temp == null || temp.getUserId() == null)
            throw new APIException(BadRequest.ADMIN_USER_NAME_NOT_EXISTS);

        //4.判断用户名或密码是否匹配
        if(!temp.getPassword().equals(PasswordCrypt.encrypt(loginReq.getPassword())))
            throw new APIException(BadRequest.ADMIN_USER_NAME_OR_PASS_INVALID);

        //5.创建 token
        loginReq.setUserId(temp.getUserId());
        AuthToken authToken = authService.createToken(loginReq);
        String token = buildAuthToken(response, authToken);
        logger.info(" --->> token: {}", token);

        //6.封装返回结果
        AuthVo authVo = BeanUtils.copyProperties(temp, AuthVo.class);
        authVo.setToken(token);
        return authVo;
    }


    public static void main(String[] args) {

        String str = "yyOQqyiela6W%2Fz%2Fmq%2B75m3XoTDFr6VCb1OTPUdgkf88xXnOZYTJEOCl01zEGN1YXAU27RozSxK4qbx9k3wDmwMxRCpo31D0CZ30NeolXQHS0BuesGEZMh5hlIq%2FxnfHN95LC2likftfd2ay%2FGM1C%2BVwjP8FADLjckYG3xDQhIUw%3D";
        AuthToken authToken = AuthToken.parse(str);

        logger.info(" ==>> {}", authToken.userId);

    }


}
