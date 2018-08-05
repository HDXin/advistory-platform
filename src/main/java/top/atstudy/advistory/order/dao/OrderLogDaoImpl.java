package top.atstudy.advistory.order.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.advistory.order.dao.dto.OrderLogDTO;
import top.atstudy.advistory.order.dao.dto.OrderLogDTOExample;
import top.atstudy.advistory.order.dao.mapper.OrderLogDTOMapper;
import top.atstudy.advistory.order.vo.req.OrderLogQuery;
import top.atstudy.component.base.BaseDao;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumOrder;

import java.util.Date;
import java.util.List;

/**
 * IOrderLogDao 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class OrderLogDaoImpl extends BaseDao implements IOrderLogDao {
    /******* Fields Area *******/
    private OrderLogDTOMapper orderLogDTOMapper;

    /******* Construction Area *******/
    public OrderLogDaoImpl(@Autowired OrderLogDTOMapper orderLogDTOMapper) {
        this.orderLogDTOMapper = orderLogDTOMapper;
    }

    @Override
    public OrderLogDTO getById(Long id) {
        return this.orderLogDTOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<OrderLogDTO> findByExample(OrderLogDTOExample example, Pagination... paginationParam) {
        Pagination pagination = paginationParam == null || paginationParam.length == 0 ? new Pagination() : paginationParam[0];
        Page<OrderLogDTO> page = new Page<OrderLogDTO>(pagination);
        String orderBySql = super.buildSortSql(page.buildSortFields());
        example.setOrderByClause(orderBySql);
        long total = this.orderLogDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<OrderLogDTO> targets = this.orderLogDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }


    @Override
    public List<OrderLogDTO> listByExample(OrderLogDTOExample example) {
        this.loadDefaultOrder(example);
        return this.orderLogDTOMapper.selectByExample(example);
    }

    @Override
    public OrderLogDTO getByExample(OrderLogDTOExample example) {
        OrderLogDTO target = null;
        this.loadDefaultOrder(example);
        List<OrderLogDTO> targets = this.orderLogDTOMapper.selectByExample(example);
        if (targets.size() > 0) {
            target = targets.get(0);
        }
        return target;
    }

    @Override
    public Long countByExample(OrderLogDTOExample example) {
        this.loadDefaultOrder(example);
        return this.orderLogDTOMapper.countByExample(example);
    }

    @Override
    public Page<OrderLogDTO> findByQuery(OrderLogQuery query) {
        Page<OrderLogDTO> page = new Page<>(query);
        OrderLogDTOExample example = this.buildQueryExample(query);

        this.loadDefaultOrder(example);
        long total = this.orderLogDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<OrderLogDTO> targets = this.orderLogDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }

    @Override
    public List<OrderLogDTO> listByQuery(OrderLogQuery query) {
        OrderLogDTOExample example = this.buildQueryExample(query);
        return this.orderLogDTOMapper.selectByExample(example);
    }


    @Override
    public Long countByQuery(OrderLogQuery query) {
        OrderLogDTOExample example = this.buildQueryExample(query);
        return this.orderLogDTOMapper.countByExample(example);
    }

    @Override
    public boolean create(OrderLogDTO target) {
        return this.orderLogDTOMapper.insertSelective(target) > 0;
    }


    @Override
    public OrderLogDTO createAndGet(OrderLogDTO target) {
        OrderLogDTO result = null;
        if (this.create(target)) {
            result = this.getById(target.getOrderLogId());
        }
        return result;
    }

    @Override
    public boolean update(OrderLogDTO target) {
        target.setUpdateTime(new Date());
        return this.orderLogDTOMapper.updateByPrimaryKeySelective(target) > 0;
    }

    @Override
    public OrderLogDTO updateAndGet(OrderLogDTO target) {
        OrderLogDTO result = null;
        if (this.update(target)) {
            result = this.getById(target.getOrderLogId());
        }
        return result;
    }

    @Override
    public boolean remove(OrderLogDTO target) {
        target.setDeleted(EnumDeleted.DELETED);
        return this.update(target);
    }

    @Override
    public boolean batchCreate(List<OrderLogDTO> targets) {
        boolean batchFlag = targets.stream().map(this::create).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchUpdate(List<OrderLogDTO> targets) {
        boolean batchFlag = targets.stream().map(this::update).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchRemove(List<OrderLogDTO> targets) {
        boolean batchFlag = targets.stream().map(this::remove).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    private void loadDefaultOrder(OrderLogDTOExample example) {
        if (StringUtils.isEmpty(example.getOrderByClause())) {
            String orderBySql = "create_time" + " " + EnumOrder.DESC.getCode();
            example.setOrderByClause(orderBySql);
        }
    }

    private OrderLogDTOExample buildQueryExample(OrderLogQuery query) {
        OrderLogDTOExample example = new OrderLogDTOExample();
        OrderLogDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        String orderBySql = super.buildSortSql(query.buildSortFields());
        example.setOrderByClause(orderBySql);
        example.limit(query.getOffset(), query.getLimit());
        return example;
    }


}
