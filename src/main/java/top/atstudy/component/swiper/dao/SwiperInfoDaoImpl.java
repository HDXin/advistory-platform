package top.atstudy.component.swiper.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.component.base.BaseDao;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumOrder;
import top.atstudy.component.swiper.dao.dto.SwiperInfoDTO;
import top.atstudy.component.swiper.dao.dto.SwiperInfoDTOExample;
import top.atstudy.component.swiper.dao.mapper.SwiperInfoDTOMapper;
import top.atstudy.component.swiper.vo.req.SwiperInfoQuery;

import java.util.Date;
import java.util.List;

/**
 * ISwiperInfoDao 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class SwiperInfoDaoImpl extends BaseDao implements ISwiperInfoDao {
    /******* Fields Area *******/
    private SwiperInfoDTOMapper swiperInfoDTOMapper;

    /******* Construction Area *******/
    public SwiperInfoDaoImpl(@Autowired SwiperInfoDTOMapper swiperInfoDTOMapper) {
        this.swiperInfoDTOMapper = swiperInfoDTOMapper;
    }

    @Override
    public SwiperInfoDTO getById(Long id) {
        return this.swiperInfoDTOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<SwiperInfoDTO> findByExample(SwiperInfoDTOExample example, Pagination... paginationParam) {
        Pagination pagination = paginationParam == null || paginationParam.length == 0 ? new Pagination() : paginationParam[0];
        Page<SwiperInfoDTO> page = new Page<SwiperInfoDTO>(pagination);
        String orderBySql = super.buildSortSql(page.buildSortFields());
        example.setOrderByClause(orderBySql);
        long total = this.swiperInfoDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<SwiperInfoDTO> targets = this.swiperInfoDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }


    @Override
    public List<SwiperInfoDTO> listByExample(SwiperInfoDTOExample example) {
        this.loadDefaultOrder(example);
        return this.swiperInfoDTOMapper.selectByExample(example);
    }

    @Override
    public SwiperInfoDTO getByExample(SwiperInfoDTOExample example) {
        SwiperInfoDTO target = null;
        this.loadDefaultOrder(example);
        List<SwiperInfoDTO> targets = this.swiperInfoDTOMapper.selectByExample(example);
        if (targets.size() > 0) {
            target = targets.get(0);
        }
        return target;
    }

    @Override
    public Long countByExample(SwiperInfoDTOExample example) {
        this.loadDefaultOrder(example);
        return this.swiperInfoDTOMapper.countByExample(example);
    }

    @Override
    public Page<SwiperInfoDTO> findByQuery(SwiperInfoQuery query) {
        Page<SwiperInfoDTO> page = new Page<>(query);
        SwiperInfoDTOExample example = this.buildQueryExample(query);

        this.loadDefaultOrder(example);
        long total = this.swiperInfoDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<SwiperInfoDTO> targets = this.swiperInfoDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }

    @Override
    public List<SwiperInfoDTO> listByQuery(SwiperInfoQuery query) {
        SwiperInfoDTOExample example = this.buildQueryExample(query);
        return this.swiperInfoDTOMapper.selectByExample(example);
    }


    @Override
    public Long countByQuery(SwiperInfoQuery query) {
        SwiperInfoDTOExample example = this.buildQueryExample(query);
        return this.swiperInfoDTOMapper.countByExample(example);
    }

    @Override
    public boolean create(SwiperInfoDTO target) {
        return this.swiperInfoDTOMapper.insertSelective(target) > 0;
    }


    @Override
    public SwiperInfoDTO createAndGet(SwiperInfoDTO target) {
        SwiperInfoDTO result = null;
        if (this.create(target)) {
            result = this.getById(target.getSwiperId());
        }
        return result;
    }

    @Override
    public boolean update(SwiperInfoDTO target) {
        target.setUpdateTime(new Date());
        return this.swiperInfoDTOMapper.updateByPrimaryKeySelective(target) > 0;
    }

    @Override
    public SwiperInfoDTO updateAndGet(SwiperInfoDTO target) {
        SwiperInfoDTO result = null;
        if (this.update(target)) {
            result = this.getById(target.getSwiperId());
        }
        return result;
    }

    @Override
    public boolean remove(SwiperInfoDTO target) {
        target.setDeleted(EnumDeleted.DELETED);
        return this.update(target);
    }

    @Override
    public boolean batchCreate(List<SwiperInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::create).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchUpdate(List<SwiperInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::update).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchRemove(List<SwiperInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::remove).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    private void loadDefaultOrder(SwiperInfoDTOExample example) {
        if (StringUtils.isEmpty(example.getOrderByClause())) {
            String orderBySql = "create_time" + " " + EnumOrder.DESC.getCode();
            example.setOrderByClause(orderBySql);
        }
    }

    private SwiperInfoDTOExample buildQueryExample(SwiperInfoQuery query) {
        SwiperInfoDTOExample example = new SwiperInfoDTOExample();
        SwiperInfoDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        String orderBySql = super.buildSortSql(query.buildSortFields());
        example.setOrderByClause(orderBySql);
        example.limit(query.getOffset(), query.getLimit());
        return example;
    }


}
