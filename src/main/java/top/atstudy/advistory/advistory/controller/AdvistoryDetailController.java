package top.atstudy.advistory.advistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.advistory.service.IAdvistoryDetailService;
import top.atstudy.advistory.advistory.vo.req.AdvistoryDetailQuery;
import top.atstudy.advistory.advistory.vo.resp.AdvistoryDetailResp;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicController;
import top.atstudy.component.exception.APIException;

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
@RequestMapping("/api/admin/advistoryDetail")
public class AdvistoryDetailController extends BasicController {
    /******* Fields Area *******/

    @Autowired
    private IAdvistoryDetailService advistoryDetailService;

    /******* Construction Area *******/
    /******* GetSet Area ******/


    @GetMapping("/{id}")
    public AdvistoryDetailResp get(@PathVariable("id") Long id) {
        AdvistoryDetailResp target = this.advistoryDetailService.getById(id);
        return target;
    }

    @GetMapping("")
    public Page<AdvistoryDetailResp> find(AdvistoryDetailQuery query) {
        Page<AdvistoryDetailResp> target = this.advistoryDetailService.findByQuery(query);
        return target;
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) throws APIException {
        this.advistoryDetailService.remove(id, super.getSessionUser());
    }

    /******* Method Area *******/


}

