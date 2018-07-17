package top.atstudy.component.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.config.Constants;
import top.atstudy.component.base.controller.BasicController;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.user.SessionUser;
import top.atstudy.component.user.service.IAdminUserService;
import top.atstudy.component.user.vo.req.AdminUserQuery;
import top.atstudy.component.user.vo.req.AdminUserReq;
import top.atstudy.component.user.vo.req.PassVo;
import top.atstudy.component.user.vo.resp.AdminUserResp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController extends BasicController {
    /******* Fields Area *******/

    @Autowired
    private IAdminUserService adminUserService;

    /******* Construction Area *******/
    /******* GetSet Area ******/

    /**
     * 创建用户
     * @param req
     * @return
     */
    @ResponseBody
    @PostMapping("")
    public AdminUserResp create(@RequestBody AdminUserReq req) throws APIException {
        SessionUser sessionUser = getSessionUser();
        return this.adminUserService.createAndGet(req, sessionUser);
    }

    /**
     * 修改用户信息
     * @param userId
     * @param req
     * @return
     */
    @ResponseBody
    @PutMapping("/{userId}")
    public AdminUserResp update(@PathVariable("userId") Long userId,
                                @RequestBody AdminUserReq req) throws APIException {
        req.setUserId(userId);
        return this.adminUserService.update(req, getSessionUser());
    }

    /**
     * 启用
     * @param userId
     * @return
     */
    @ResponseBody
    @PutMapping("/{userId}/enable")
    public Boolean enable(@PathVariable("userId") Long userId){
        return this.adminUserService.enable(userId, getSessionUser());
    }

    /**
     * 禁用
     * @param userId
     * @return
     */
    @ResponseBody
    @PutMapping("/{userId}/disable")
    public Boolean disable(@PathVariable("userId") Long userId){
        return this.adminUserService.disable(userId, getSessionUser());
    }

    /**
     * 检查用户名是否重复
     * @param userId 创建用户是, 为空
     * @param userName 不能为空
     * @return
     */
    @ResponseBody
    @GetMapping("/valid/username")
    public Boolean valid(@RequestParam(value = "userId", required = false) Long userId,
                          @RequestParam("userName") String userName){
        return this.adminUserService.validUserName(userId, userName);
    }

    /**
     * 密码重置
     * @param userId
     * @return
     */
    @ResponseBody
    @PutMapping("/{userId}/pass/reset")
    public Boolean passReset(@PathVariable("userId") Long userId){
        return this.adminUserService.passReset(userId, getSessionUser());
    }

    /**
     * 密码修改
     * @param passVo
     * @return
     */
    @ResponseBody
    @PutMapping("/pass/update")
    public Boolean passUpdate(@RequestBody PassVo passVo) throws APIException {
        SessionUser sessionUser = getSessionUser();
        passVo.setUserId(sessionUser.getUserId());
        return this.adminUserService.passUpdate(passVo, getSessionUser());
    }

    /**
     * 获取指定用户信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public AdminUserResp get(@PathVariable("id") Long id) {
        AdminUserResp target = this.adminUserService.getById(id);
        return target;
    }

    /**
     * 系统用户列表
     * @param query
     * @return
     */
    @GetMapping("")
    public Page<AdminUserResp> find(AdminUserQuery query) {
        Page<AdminUserResp> target = this.adminUserService.findByQuery(query);
        return target;
    }

    /**
     * 删除指定用户
     * @param userId
     * @throws APIException
     */
    @DeleteMapping("/{userId}")
    public void remove(@PathVariable("userId") Long userId) throws APIException {
        this.adminUserService.remove(userId, getSessionUser());
    }

    /******* Method Area *******/


}

