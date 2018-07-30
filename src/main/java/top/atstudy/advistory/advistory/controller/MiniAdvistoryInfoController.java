package top.atstudy.advistory.advistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.advistory.service.IAdvistoryInfoService;
import top.atstudy.advistory.advistory.vo.req.AdvistoryInfoQuery;
import top.atstudy.advistory.advistory.vo.resp.AdvistoryInfoResp;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicAppController;

@RestController
@RequestMapping("/api/mini/advistoryInfo")
public class MiniAdvistoryInfoController extends BasicAppController {
    /******* Fields Area *******/

    @Autowired
    private IAdvistoryInfoService advistoryInfoService;

    /******* Construction Area *******/
    /******* GetSet Area ******/

    /**
     * 咨询: 详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public AdvistoryInfoResp get(@PathVariable("id") Long id) {
        AdvistoryInfoResp target = this.advistoryInfoService.getById(id);

        return target;
    }

    /**
     * 咨询: 列表
     * @param query
     * @return
     */
    @GetMapping("")
    public Page<AdvistoryInfoResp> find(AdvistoryInfoQuery query) {
        Page<AdvistoryInfoResp> target = this.advistoryInfoService.findByQuery(query);
        return target;
    }

}

