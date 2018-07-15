package top.atstudy.component.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.component.auth.service.AuthService;
import top.atstudy.component.auth.vo.AuthVo;
import top.atstudy.component.auth.vo.LoginReq;
import top.atstudy.component.base.config.AuthToken;
import top.atstudy.component.base.controller.BasicController;
import top.atstudy.component.exception.FrameworkException;

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

    @PostMapping("/mini/login")
    public String miniLogin(@RequestBody AuthVo authVo){

//        AuthToken authToken = new AuthToken();


        return null;
    }

    @PostMapping("/admin/login")
    public AuthVo adminLogin(HttpServletResponse response, @RequestBody LoginReq loginReq) throws FrameworkException {

        AuthToken authToken = authService.createToken(loginReq);
        String token = buildAuthToken(response, authToken);
        logger.info(" --->> token: {}", token);

        AuthVo authVo = new AuthVo();
        authVo.setToken(token);
        authVo.setUsername(loginReq.getUsername());
        return authVo;
    }


    public static void main(String[] args) {

        String str = "yyOQqyiela6W%2Fz%2Fmq%2B75m3XoTDFr6VCb1OTPUdgkf88xXnOZYTJEOCl01zEGN1YXAU27RozSxK4qbx9k3wDmwMxRCpo31D0CZ30NeolXQHS0BuesGEZMh5hlIq%2FxnfHN95LC2likftfd2ay%2FGM1C%2BVwjP8FADLjckYG3xDQhIUw%3D";
        AuthToken authToken = AuthToken.parse(str);

        logger.info(" ==>> {}", authToken.userId);



    }


}
