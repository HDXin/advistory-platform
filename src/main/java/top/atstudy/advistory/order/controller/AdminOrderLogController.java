package top.atstudy.advistory.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.order.service.IOrderLogService;
import top.atstudy.advistory.order.vo.req.OrderLogQuery;
import top.atstudy.advistory.order.vo.resp.OrderLogResp;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicAdminController;
import top.atstudy.component.exception.APIException;

/**
 * @author Sudao @ HuangDexin
 * @email : huangdexin@kuaicto.com or 735513870@qq.com
 * @date: 2017/11/15
 * @time: 下午1:27
 * =========================================
 * <p>
 * Contributors :
 * Tim Zhang - 2017/11/15 下午1:27
 */
@RestController
@RequestMapping("/api/admin/orderLog")
public class AdminOrderLogController extends BasicAdminController {
    /******* Fields Area *******/

    @Autowired
    private IOrderLogService orderLogService;

    /******* Construction Area *******/
    /******* GetSet Area ******/


    @GetMapping("/{id}")
    public OrderLogResp get(@PathVariable("id") Long id) {
        OrderLogResp target = this.orderLogService.getById(id);
        return target;
    }

    @GetMapping("")
    public Page<OrderLogResp> find(OrderLogQuery query) {
        Page<OrderLogResp> target = this.orderLogService.findByQuery(query);
        return target;
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) throws APIException {
        this.orderLogService.remove(id, super.getSessionUser());
    }

    /******* Method Area *******/


}

