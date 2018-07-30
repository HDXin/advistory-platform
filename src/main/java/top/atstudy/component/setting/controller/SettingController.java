package top.atstudy.component.setting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicAdminController;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.setting.service.ISettingService;
import top.atstudy.component.setting.vo.req.SettingQuery;
import top.atstudy.component.setting.vo.req.SettingReq;
import top.atstudy.component.setting.vo.resp.SettingResp;

@RestController
@RequestMapping("/api/admin/setting")
public class SettingController extends BasicAdminController {
    /******* Fields Area *******/

    @Autowired
    private ISettingService settingService;

    /******* Construction Area *******/
    /******* GetSet Area ******/

    /**
     * 编辑配置
     * @param req
     * @return
     */
    @PutMapping("/key")
    public SettingResp updateByKey(@RequestBody SettingReq req){
        return this.settingService.updateByKey(req, getSessionUser());
    }

    @GetMapping("/key")
    public SettingResp getByKey(@RequestParam("configKey") String configKey){
        return this.settingService.getByKey(configKey);
    }

}

