package top.atstudy.advistory.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.advistory.order.dao.IOrderInfoDao;
import top.atstudy.advistory.order.dao.dto.OrderInfoDTO;
import top.atstudy.advistory.order.dao.dto.OrderInfoDTOExample;
import top.atstudy.advistory.order.vo.req.OrderInfoQuery;
import top.atstudy.advistory.order.vo.req.OrderInfoReq;
import top.atstudy.advistory.order.vo.resp.OrderInfoResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.enums.EnumDeleted;

import java.util.List;

/**
 * IOrderInfoService 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {
    /******* Fields Area *******/

    private IOrderInfoDao orderInfoDao;

    /******* Construction Area *******/
    public OrderInfoServiceImpl(@Autowired IOrderInfoDao orderInfoDao) {
        this.orderInfoDao = orderInfoDao;
    }

    @Override
    public OrderInfoResp getById(Long id) {
        OrderInfoResp target = null;
        OrderInfoDTOExample example = new OrderInfoDTOExample();
        OrderInfoDTOExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(id);
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        List<OrderInfoDTO> targets = this.orderInfoDao.listByExample(example);
        OrderInfoDTO targetDto = this.orderInfoDao.getByExample(example);
        if (targetDto != null) {
            target = OrderInfoResp.parseSinglet(targetDto);
        }
        return target;
    }



    @Override
    public Page<OrderInfoResp> findByQuery(OrderInfoQuery query) {
        Page<OrderInfoDTO> targetPage = this.orderInfoDao.findByQuery(query);
        Page<OrderInfoResp> page = new Page<>(targetPage);
        page.setItems(OrderInfoResp.parseList(targetPage.getItems()));
        return page;
    }

    @Override
    public List<OrderInfoResp> listByQuery(OrderInfoQuery query) {
        List<OrderInfoDTO> targets = this.orderInfoDao.listByQuery(query);
        return OrderInfoResp.parseList(targets);
    }

    @Override
    public Long countByQuery(OrderInfoQuery query) {
        return this.orderInfoDao.countByQuery(query);
    }

    @Override
    public OrderInfoResp createAndGet(OrderInfoReq req, IOperatorAware operator) {
        OrderInfoDTO target = req.convertToDTO();
        target.setOperator(operator, true);
        target = this.orderInfoDao.createAndGet(target);
        return OrderInfoResp.parseSinglet(target);
    }

    @Override
    public OrderInfoResp update(OrderInfoReq req, IOperatorAware operator) {
        OrderInfoDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        target = this.orderInfoDao.updateAndGet(target);
        return OrderInfoResp.parseSinglet(target);
    }

    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        OrderInfoDTO target = this.orderInfoDao.getById(id);
        target.setOperator(operator, false);
        return this.orderInfoDao.remove(target);
    }
    /******* GetSet Area ******/

    /******* Method Area *******/


}
