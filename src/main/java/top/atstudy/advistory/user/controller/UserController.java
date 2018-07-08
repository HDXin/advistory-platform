package top.atstudy.advistory.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.user.dao.IUserDao;
import top.atstudy.advistory.user.dao.dto.UserDTO;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-08
 * Time: 23:47
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserDao userDao;

    @ResponseBody
    @GetMapping("/{userId}")
    public UserDTO getById(@PathVariable("userId") Long userId){
        return this.userDao.getById(userId);
    }

}
