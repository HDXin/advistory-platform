package top.atstudy.advistory.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.advistory.order.dao.IOrderLogDao;
import top.atstudy.advistory.order.dao.dto.OrderLogDTO;
import top.atstudy.advistory.order.dao.dto.OrderLogDTOExample;
import top.atstudy.advistory.order.vo.req.OrderLogQuery;
import top.atstudy.advistory.order.vo.req.OrderLogReq;
import top.atstudy.advistory.order.vo.resp.OrderLogResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.enums.EnumDeleted;

import java.util.List;

/**
 * IOrderLogService 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class OrderLogServiceImpl implements IOrderLogService {
    /******* Fields Area *******/

    private IOrderLogDao orderLogDao;

    /******* Construction Area *******/
    public OrderLogServiceImpl(@Autowired IOrderLogDao orderLogDao) {
        this.orderLogDao = orderLogDao;
    }

    @Override
    public OrderLogResp getById(Long id) {
        OrderLogResp target = null;
        OrderLogDTOExample example = new OrderLogDTOExample();
        OrderLogDTOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderLogIdEqualTo(id);
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        List<OrderLogDTO> targets = this.orderLogDao.listByExample(example);
        OrderLogDTO targetDto = this.orderLogDao.getByExample(example);
        if (targetDto != null) {
            target = OrderLogResp.parseSinglet(targetDto);
        }
        return target;
    }



    @Override
    public Page<OrderLogResp> findByQuery(OrderLogQuery query) {
        Page<OrderLogDTO> targetPage = this.orderLogDao.findByQuery(query);
        Page<OrderLogResp> page = new Page<>(targetPage);
        page.setItems(OrderLogResp.parseList(targetPage.getItems()));
        return page;
    }

    @Override
    public List<OrderLogResp> listByQuery(OrderLogQuery query) {
        List<OrderLogDTO> targets = this.orderLogDao.listByQuery(query);
        return OrderLogResp.parseList(targets);
    }

    @Override
    public Long countByQuery(OrderLogQuery query) {
        return this.orderLogDao.countByQuery(query);
    }

    @Override
    public OrderLogResp createAndGet(OrderLogReq req, IOperatorAware operator) {
        OrderLogDTO target = req.convertToDTO();
        target.setOperator(operator, true);
        target = this.orderLogDao.createAndGet(target);
        return OrderLogResp.parseSinglet(target);
    }

    @Override
    public OrderLogResp update(OrderLogReq req, IOperatorAware operator) {
        OrderLogDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        target = this.orderLogDao.updateAndGet(target);
        return OrderLogResp.parseSinglet(target);
    }

    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        OrderLogDTO target = this.orderLogDao.getById(id);
        target.setOperator(operator, false);
        return this.orderLogDao.remove(target);
    }
    /******* GetSet Area ******/

    /******* Method Area *******/


}
