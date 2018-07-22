package top.atstudy.component.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicController;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.user.service.IAppUserService;
import top.atstudy.component.user.vo.req.AppUserQuery;
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
@RequestMapping("/api/admin/appUser")
public class AppUserController extends BasicController {
    /******* Fields Area *******/

    @Autowired
    private IAppUserService appUserService;

    /******* Construction Area *******/
    /******* GetSet Area ******/


    @GetMapping("/{id}")
    public AppUserResp get(@PathVariable("id") Long id) {
        AppUserResp target = this.appUserService.getById(id);
        return target;
    }

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

