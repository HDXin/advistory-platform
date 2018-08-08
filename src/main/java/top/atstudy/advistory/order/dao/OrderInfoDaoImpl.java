package top.atstudy.advistory.order.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.advistory.order.dao.dto.OrderInfoDTO;
import top.atstudy.advistory.order.dao.dto.OrderInfoDTOExample;
import top.atstudy.advistory.order.dao.mapper.OrderInfoDTOMapper;
import top.atstudy.advistory.order.vo.req.OrderInfoQuery;
import top.atstudy.component.base.BaseDao;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumOrder;
import top.atstudy.component.enums.EnumOrderStatus;
import top.atstudy.component.enums.EnumPaymentStatus;

import java.util.Date;
import java.util.List;

/**
 * IOrderInfoDao 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class OrderInfoDaoImpl extends BaseDao implements IOrderInfoDao {
    /******* Fields Area *******/
    private OrderInfoDTOMapper orderInfoDTOMapper;

    /******* Construction Area *******/
    public OrderInfoDaoImpl(@Autowired OrderInfoDTOMapper orderInfoDTOMapper) {
        this.orderInfoDTOMapper = orderInfoDTOMapper;
    }

    @Override
    public OrderInfoDTO getById(Long id) {
        return this.orderInfoDTOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<OrderInfoDTO> findByExample(OrderInfoDTOExample example, Pagination... paginationParam) {
        Pagination pagination = paginationParam == null || paginationParam.length == 0 ? new Pagination() : paginationParam[0];
        Page<OrderInfoDTO> page = new Page<OrderInfoDTO>(pagination);
        String orderBySql = super.buildSortSql(page.buildSortFields());
        example.setOrderByClause(orderBySql);
        long total = this.orderInfoDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<OrderInfoDTO> targets = this.orderInfoDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }


    @Override
    public List<OrderInfoDTO> listByExample(OrderInfoDTOExample example) {
        this.loadDefaultOrder(example);
        return this.orderInfoDTOMapper.selectByExample(example);
    }

    @Override
    public OrderInfoDTO getByExample(OrderInfoDTOExample example) {
        OrderInfoDTO target = null;
        this.loadDefaultOrder(example);
        List<OrderInfoDTO> targets = this.orderInfoDTOMapper.selectByExample(example);
        if (targets.size() > 0) {
            target = targets.get(0);
        }
        return target;
    }

    @Override
    public Long countByExample(OrderInfoDTOExample example) {
        this.loadDefaultOrder(example);
        return this.orderInfoDTOMapper.countByExample(example);
    }

    @Override
    public Page<OrderInfoDTO> findByQuery(OrderInfoQuery query) {
        Page<OrderInfoDTO> page = new Page<>(query);
        OrderInfoDTOExample example = this.buildQueryExample(query);

        this.loadDefaultOrder(example);
        long total = this.orderInfoDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<OrderInfoDTO> targets = this.orderInfoDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }

    @Override
    public List<OrderInfoDTO> listByQuery(OrderInfoQuery query) {
        OrderInfoDTOExample example = this.buildQueryExample(query);
        return this.orderInfoDTOMapper.selectByExample(example);
    }


    @Override
    public Long countByQuery(OrderInfoQuery query) {
        OrderInfoDTOExample example = this.buildQueryExample(query);
        return this.orderInfoDTOMapper.countByExample(example);
    }

    @Override
    public boolean create(OrderInfoDTO target) {
        return this.orderInfoDTOMapper.insertSelective(target) > 0;
    }


    @Override
    public OrderInfoDTO createAndGet(OrderInfoDTO target) {
        OrderInfoDTO result = null;
        if (this.create(target)) {
            result = this.getById(target.getOrderId());
        }
        return result;
    }

    @Override
    public boolean update(OrderInfoDTO target) {
        target.setUpdateTime(new Date());
        return this.orderInfoDTOMapper.updateByPrimaryKeySelective(target) > 0;
    }

    @Override
    public OrderInfoDTO updateAndGet(OrderInfoDTO target) {
        OrderInfoDTO result = null;
        if (this.update(target)) {
            result = this.getById(target.getOrderId());
        }
        return result;
    }

    @Override
    public boolean remove(OrderInfoDTO target) {
        target.setDeleted(EnumDeleted.DELETED);
        return this.update(target);
    }

    @Override
    public boolean batchCreate(List<OrderInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::create).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchUpdate(List<OrderInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::update).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchRemove(List<OrderInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::remove).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public OrderInfoDTO getByOrderNo(String orderNo) {

        if(StringUtils.isBlank(orderNo))
            return null;

        OrderInfoDTOExample example = new OrderInfoDTOExample();
        OrderInfoDTOExample.Criteria criteria = example.createCriteria();

        criteria.andDeletedEqualTo(EnumDeleted.NORMAL)
                .andOrderStatusEqualTo(EnumOrderStatus.NEW_ORDER)
                .andPaymentStatusEqualTo(EnumPaymentStatus.NEW_ORDER)
                .andOrderNoEqualTo(orderNo);

        List<OrderInfoDTO> targets = this.orderInfoDTOMapper.selectByExample(example);
        return CollectionUtils.isEmpty(targets) ? null:targets.get(0);
    }

    private void loadDefaultOrder(OrderInfoDTOExample example) {
        if (StringUtils.isEmpty(example.getOrderByClause())) {
            String orderBySql = "create_time" + " " + EnumOrder.DESC.getCode();
            example.setOrderByClause(orderBySql);
        }
    }

    private OrderInfoDTOExample buildQueryExample(OrderInfoQuery query) {
        OrderInfoDTOExample example = new OrderInfoDTOExample();
        OrderInfoDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        String orderBySql = super.buildSortSql(query.buildSortFields());
        example.setOrderByClause(orderBySql);
        example.limit(query.getOffset(), query.getLimit());
        return example;
    }


}
