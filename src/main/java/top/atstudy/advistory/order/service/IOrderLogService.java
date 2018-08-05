package top.atstudy.advistory.order.service;

import top.atstudy.advistory.order.vo.req.OrderLogQuery;
import top.atstudy.advistory.order.vo.req.OrderLogReq;
import top.atstudy.advistory.order.vo.resp.OrderLogResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import java.util.List;

/**
 * IOrderLogService 接口
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IOrderLogService {

    OrderLogResp getById(Long id);

    Page<OrderLogResp> findByQuery(OrderLogQuery query);

    List<OrderLogResp> listByQuery(OrderLogQuery query);

    Long countByQuery(OrderLogQuery query);

    OrderLogResp createAndGet(OrderLogReq req, IOperatorAware operator);

    OrderLogResp update(OrderLogReq req, IOperatorAware operator);

    boolean remove(Long id, IOperatorAware operator);


}

