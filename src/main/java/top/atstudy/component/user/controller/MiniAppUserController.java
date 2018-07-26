package top.atstudy.component.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicAppController;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.user.AppSessionUser;
import top.atstudy.component.user.service.IAppUserService;
import top.atstudy.component.user.vo.req.AppUserQuery;
import top.atstudy.component.user.vo.req.AppUserReq;
import top.atstudy.component.user.vo.resp.AppUserResp;

/**
 * smart-mybatis-spring-boot-starter
 * <p>
 * Description :
 * <p>
 * Creator :
 *
 * @author Sudao @ Tim Zhang
 * @email : zhanglong@kuaicto.com or solidsnake2007@gmail.com
 * @date: 2017/11/15
 * @time: 下午1:27
 * =========================================
 * <p>
 * Contributors :
 * Tim Zhang - 2017/11/15 下午1:27
 */
@RestController
@RequestMapping("/api/mini")
public class MiniAppUserController extends BasicAppController {
    /******* Fields Area *******/

    @Autowired
    private IAppUserService appUserService;

    /******* Construction Area *******/
    /******* GetSet Area ******/

    /**
     * 用户资料
     * @return
     */
    @GetMapping("/profile")
    public AppSessionUser get() {
        return getSessionUser();
    }

    /**
     * 编辑用户资料
     * @return
     */
    @PutMapping("/profile")
    public AppUserResp update(@RequestBody AppUserReq req) {
        AppSessionUser sessionUser = getSessionUser();
        req.setUserId(sessionUser.getUserId());
        return this.appUserService.update(req, sessionUser);
    }

    /**
     * 启用
     * @param userId
     * @return
     */
    @PutMapping("/{userId}/enable")
    public boolean enable(@PathVariable("userId") Long userId){
        return this.appUserService.enable(userId, getSessionUser());
    }

    /**
     * 禁用
     * @param userId
     * @return
     */
    @PutMapping("/{userId}/disable")
    public boolean disable(@PathVariable("userId") Long userId){
        return this.appUserService.disable(userId, getSessionUser());
    }

    /**
     * 用户列表
     * @param query
     * @return
     */
    @GetMapping("")
    public Page<AppUserResp> find(AppUserQuery query) {
        Page<AppUserResp> target = this.appUserService.findByQuery(query);
        return target;
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) throws APIException {
        this.appUserService.remove(id, super.getSessionUser());
    }

    /******* Method Area *******/


}

