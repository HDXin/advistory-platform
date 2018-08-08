package top.atstudy.advistory.order.service;

import top.atstudy.advistory.order.vo.req.OrderInfoQuery;
import top.atstudy.advistory.order.vo.req.OrderInfoReq;
import top.atstudy.advistory.order.vo.resp.OrderInfoResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.user.AppSessionUser;
import top.atstudy.sdk.payment.wechat.vo.UnifiedOrderResp;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * IOrderInfoService 接口
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IOrderInfoService {

    OrderInfoResp getById(Long id);

    Page<OrderInfoResp> findByQuery(OrderInfoQuery query);

    List<OrderInfoResp> listByQuery(OrderInfoQuery query);

    Long countByQuery(OrderInfoQuery query);

    OrderInfoResp createAndGet(OrderInfoReq req, IOperatorAware operator) throws APIException;

    OrderInfoResp update(OrderInfoReq req, IOperatorAware operator);

    boolean remove(Long id, IOperatorAware operator);

    /**
     * 预下单
     * @param req
     * @param sessionUser
     * @return
     */
    UnifiedOrderResp prepay(OrderInfoReq req, AppSessionUser sessionUser) throws InvocationTargetException, IllegalAccessException, APIException;
}

