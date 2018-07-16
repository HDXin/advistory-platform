package top.atstudy.component.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicController;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.user.service.IAdminUserService;
import top.atstudy.component.user.vo.req.AdminUserQuery;
import top.atstudy.component.user.vo.req.AdminUserReq;
import top.atstudy.component.user.vo.resp.AdminUserResp;

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
@RequestMapping("/admin/user")
public class AdminUserController extends BasicController {
    /******* Fields Area *******/

    @Autowired
    private IAdminUserService adminUserService;

    /******* Construction Area *******/
    /******* GetSet Area ******/

    @ResponseBody
    @PostMapping("")
    public AdminUserResp create(@RequestBody AdminUserReq req){
        return this.adminUserService.createAndGet(req, getSessionUser());
    }

    @ResponseBody
    @PutMapping("")
    public AdminUserResp update(@PathVariable("userId") Long userId,
                                @RequestBody AdminUserReq req){
        req.setUserId(userId);
        return this.adminUserService.update(req, getSessionUser());
    }

    @GetMapping("/{id}")
    public AdminUserResp get(@PathVariable("id") Long id) {
        AdminUserResp target = this.adminUserService.getById(id);
        return target;
    }

    @GetMapping("")
    public Page<AdminUserResp> find(AdminUserQuery query) {
        Page<AdminUserResp> target = this.adminUserService.findByQuery(query);
        return target;
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) throws APIException {
        this.adminUserService.remove(id, null);
    }

    /******* Method Area *******/


}

