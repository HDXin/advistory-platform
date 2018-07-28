package top.atstudy.advistory.advistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.advistory.service.IAdvistoryInfoService;
import top.atstudy.advistory.advistory.vo.req.AdvistoryInfoQuery;
import top.atstudy.advistory.advistory.vo.req.AdvistoryInfoReq;
import top.atstudy.advistory.advistory.vo.resp.AdvistoryInfoResp;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicAdminController;
import top.atstudy.component.exception.APIException;
import java.text.ParseException;

@RestController
@RequestMapping("/api/admin/advistoryInfo")
public class AdvistoryInfoController extends BasicAdminController {
    /******* Fields Area *******/

    @Autowired
    private IAdvistoryInfoService advistoryInfoService;

    /******* Construction Area *******/
    /******* GetSet Area ******/

    /**
     * 咨询: 新增
     * @param req
     * @return
     */
    @PostMapping("")
    public AdvistoryInfoResp create(@RequestBody AdvistoryInfoReq req) throws ParseException {
        return this.advistoryInfoService.createAndGet(req, getSessionUser());
    }

    /**
     * 咨询: 编辑
     * @param advistoryId
     * @param req
     * @return
     */
    @PutMapping("/{advistoryId}")
    public AdvistoryInfoResp update(@PathVariable("advistoryId") Long advistoryId,
                                    @RequestBody AdvistoryInfoReq req) throws ParseException {
        req.setAdvistoryId(advistoryId);
        return this.advistoryInfoService.update(req, getSessionUser());
    }

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

    /**
     * 咨询: 删除
     * @param id
     * @throws APIException
     */
    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) throws APIException {
        this.advistoryInfoService.remove(id, super.getSessionUser());
    }

    /******* Method Area *******/


}

