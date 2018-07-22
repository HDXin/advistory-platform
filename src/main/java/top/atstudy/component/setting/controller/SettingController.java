package top.atstudy.component.setting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicController;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.setting.service.ISettingService;
import top.atstudy.component.setting.vo.req.SettingQuery;
import top.atstudy.component.setting.vo.resp.SettingResp;

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
@RequestMapping("/admin/setting")
public class SettingController extends BasicController {
    /******* Fields Area *******/

    @Autowired
    private ISettingService settingService;

    /******* Construction Area *******/
    /******* GetSet Area ******/


    @GetMapping("/{id}")
    public SettingResp get(@PathVariable("id") Long id) {
        SettingResp target = this.settingService.getById(id);
        return target;
    }

    @GetMapping("")
    public Page<SettingResp> find(SettingQuery query) {
        Page<SettingResp> target = this.settingService.findByQuery(query);
        return target;
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) throws APIException {
        this.settingService.remove(id, super.getSessionUser());
    }

    /******* Method Area *******/


}

