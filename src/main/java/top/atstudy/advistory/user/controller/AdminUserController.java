package top.atstudy.advistory.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.base.enums.BadRequest;
import top.atstudy.advistory.base.enums.Forbidden;
import top.atstudy.advistory.base.enums.Unauthorized;
import top.atstudy.advistory.user.dao.IUserDao;
import top.atstudy.advistory.user.dao.dto.UserDTO;
import top.atstudy.component.exception.APIException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-08
 * Time: 23:47
 */
@RestController
@RequestMapping("/admin/adminUser")
public class AdminUserController {

    @Autowired
    private IUserDao userDao;

    @ResponseBody
    @PostMapping("")
    public UserDTO create(@RequestBody UserDTO req){

        UserDTO resp = new UserDTO();

        if(req.getUserId() != null && req.getUserId().equals(0L)){

            throw new RuntimeException("hahaha");
        }

        resp.setUserId(123L);
        resp.setUsername("Tom");

        return resp;
    }

    @ResponseBody
    @PutMapping("/{userId}")
    public UserDTO update(@PathVariable("userId") Long userId,
                          @RequestBody UserDTO req) throws APIException {
        if(userId.equals(400L)){
            throw new APIException(BadRequest.PARAMS_INVALID);
        }else if(userId.equals(401L)){
            throw new APIException(Unauthorized.USER_LOGIN_AUTH_FAILED);
        }else if(userId.equals(403L)){
            throw new APIException(Forbidden.SERVER_FORBIDDEN);
        }

        return req;
    }


    @ResponseBody
    @GetMapping("/{userId}")
    public UserDTO get(@PathVariable("userId") Long userId){
        return this.userDao.getById(userId);
    }

}
