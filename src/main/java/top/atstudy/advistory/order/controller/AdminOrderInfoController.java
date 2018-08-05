package top.atstudy.advistory.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.order.service.IOrderInfoService;
import top.atstudy.advistory.order.vo.req.OrderInfoQuery;
import top.atstudy.advistory.order.vo.resp.OrderInfoResp;
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
@RequestMapping("/api/admin/orderInfo")
public class AdminOrderInfoController extends BasicAdminController {
    /******* Fields Area *******/

    @Autowired
    private IOrderInfoService orderInfoService;

    /******* Construction Area *******/
    /******* GetSet Area ******/


    @GetMapping("/{id}")
    public OrderInfoResp get(@PathVariable("id") Long id) {
        OrderInfoResp target = this.orderInfoService.getById(id);
        return target;
    }

    @GetMapping("")
    public Page<OrderInfoResp> find(OrderInfoQuery query) {
        Page<OrderInfoResp> target = this.orderInfoService.findByQuery(query);
        return target;
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) throws APIException {
        this.orderInfoService.remove(id, super.getSessionUser());
    }

    /******* Method Area *******/


}

