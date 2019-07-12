package top.atstudy.advistory.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.order.service.IOrderInfoService;
import top.atstudy.component.base.controller.BasicAdminController;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.sdk.payment.wxpay.mini.vo.PayNotifyReq;
import top.atstudy.component.sdk.payment.wxpay.mini.vo.PayNotifyResp;

/**
 * @author Sudao @ HuangDexin
 * @email : huangdexin@kuaicto.com or 735513870@qq.com
 * @date: 2017/11/15
 * @time: 下午1:27
 * =========================================
 * <p>
 * Contributors :
 * harley - 2017/11/15 下午1:27
 */
@RestController
@RequestMapping("/payment")
public class PaymentController extends BasicAdminController {
    /******* Fields Area *******/

    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 微信服务器回调
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/notify", produces = MediaType.TEXT_XML_VALUE)
    public PayNotifyResp callback(@RequestBody PayNotifyReq req) throws APIException {
        return this.orderInfoService.callback(req, getSessionUser());
    }

}

