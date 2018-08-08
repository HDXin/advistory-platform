package top.atstudy.advistory.order.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.base.config.LocalPayConfig;
import top.atstudy.advistory.base.enums.http.BadRequest;
import top.atstudy.advistory.order.service.IOrderInfoService;
import top.atstudy.advistory.order.vo.req.OrderInfoReq;
import top.atstudy.advistory.order.vo.resp.OrderInfoResp;
import top.atstudy.component.base.controller.BasicAppController;
import top.atstudy.component.base.util.BeanUtils;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.user.AppSessionUser;
import top.atstudy.sdk.payment.wechat.config.PayConfig;
import top.atstudy.sdk.payment.wechat.service.PaymentService;
import top.atstudy.sdk.payment.wechat.vo.UnifiedOrderReq;
import top.atstudy.sdk.payment.wechat.vo.UnifiedOrderResp;
import java.lang.reflect.InvocationTargetException;

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
@RequestMapping("/api/mini/orderInfo")
public class MiniOrderInfoController extends BasicAppController {
    /******* Fields Area *******/

    @Autowired
    private IOrderInfoService orderInfoService;

    /******* Construction Area *******/
    /******* GetSet Area ******/

    /**
     * 购买会员
     * @param req
     * @return
     */
    @PostMapping("/purchase")
    public OrderInfoResp create(@RequestBody OrderInfoReq req) throws APIException {
        return this.orderInfoService.createAndGet(req, getSessionUser());
    }

    /**
     * 预支付
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/prepay")
    public UnifiedOrderResp prepay(@RequestBody OrderInfoReq req) throws InvocationTargetException, IllegalAccessException, APIException {

        //1.判断订单号不能为空
        if(StringUtils.isBlank(req.getOrderNo()))
            throw new APIException(BadRequest.ORDER_INFO_ORDERNO_IS_NULL);

        return this.orderInfoService.prepay(req, getSessionUser());
    }



}

