package top.atstudy.advistory.order.service;

import top.atstudy.advistory.order.vo.req.OrderInfoQuery;
import top.atstudy.advistory.order.vo.req.OrderInfoReq;
import top.atstudy.advistory.order.vo.resp.OrderInfoResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;

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

    OrderInfoResp createAndGet(OrderInfoReq req, IOperatorAware operator);

    OrderInfoResp update(OrderInfoReq req, IOperatorAware operator);

    boolean remove(Long id, IOperatorAware operator);


}

