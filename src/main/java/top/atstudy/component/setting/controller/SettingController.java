package top.atstudy.component.setting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicAdminController;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.setting.service.ISettingService;
import top.atstudy.component.setting.vo.req.SettingQuery;
import top.atstudy.component.setting.vo.resp.SettingResp;

@RestController
@RequestMapping("/api/admin/setting")
public class SettingController extends BasicAdminController {
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

