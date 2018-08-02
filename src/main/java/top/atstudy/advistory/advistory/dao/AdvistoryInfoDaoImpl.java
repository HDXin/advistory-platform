package top.atstudy.advistory.advistory.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.advistory.advistory.dao.dto.AdvistoryInfoDTO;
import top.atstudy.advistory.advistory.dao.dto.AdvistoryInfoDTOExample;
import top.atstudy.advistory.advistory.dao.mapper.AdvistoryInfoDTOMapper;
import top.atstudy.advistory.advistory.vo.req.AdvistoryInfoQuery;
import top.atstudy.component.base.BaseDao;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumOrder;
import java.util.Date;
import java.util.List;

/**
 * IAdvistoryInfoDao 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class AdvistoryInfoDaoImpl extends BaseDao implements IAdvistoryInfoDao {
    /******* Fields Area *******/
    private AdvistoryInfoDTOMapper advistoryInfoDTOMapper;

    /******* Construction Area *******/
    public AdvistoryInfoDaoImpl(@Autowired AdvistoryInfoDTOMapper advistoryInfoDTOMapper) {
        this.advistoryInfoDTOMapper = advistoryInfoDTOMapper;
    }

    @Override
    public AdvistoryInfoDTO getById(Long id) {
        return this.advistoryInfoDTOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<AdvistoryInfoDTO> findByExample(AdvistoryInfoDTOExample example, Pagination... paginationParam) {
        Pagination pagination = paginationParam == null || paginationParam.length == 0 ? new Pagination() : paginationParam[0];
        Page<AdvistoryInfoDTO> page = new Page<AdvistoryInfoDTO>(pagination);
        String orderBySql = super.buildSortSql(page.buildSortFields());
        example.setOrderByClause(orderBySql);
        long total = this.advistoryInfoDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<AdvistoryInfoDTO> targets = this.advistoryInfoDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }


    @Override
    public List<AdvistoryInfoDTO> listByExample(AdvistoryInfoDTOExample example) {
        this.loadDefaultOrder(example);
        return this.advistoryInfoDTOMapper.selectByExample(example);
    }

    @Override
    public AdvistoryInfoDTO getByExample(AdvistoryInfoDTOExample example) {
        AdvistoryInfoDTO target = null;
        this.loadDefaultOrder(example);
        List<AdvistoryInfoDTO> targets = this.advistoryInfoDTOMapper.selectByExample(example);
        if (targets.size() > 0) {
            target = targets.get(0);
        }
        return target;
    }

    @Override
    public Long countByExample(AdvistoryInfoDTOExample example) {
        this.loadDefaultOrder(example);
        return this.advistoryInfoDTOMapper.countByExample(example);
    }

    @Override
    public Page<AdvistoryInfoDTO> findByQuery(AdvistoryInfoQuery query) {
        Page<AdvistoryInfoDTO> page = new Page<>(query);
        AdvistoryInfoDTOExample example = this.buildQueryExample(query);

        this.loadDefaultOrder(example);
        long total = this.advistoryInfoDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<AdvistoryInfoDTO> targets = this.advistoryInfoDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }

    @Override
    public List<AdvistoryInfoDTO> listByQuery(AdvistoryInfoQuery query) {
        AdvistoryInfoDTOExample example = this.buildQueryExample(query);
        return this.advistoryInfoDTOMapper.selectByExample(example);
    }


    @Override
    public Long countByQuery(AdvistoryInfoQuery query) {
        AdvistoryInfoDTOExample example = this.buildQueryExample(query);
        return this.advistoryInfoDTOMapper.countByExample(example);
    }

    @Override
    public boolean create(AdvistoryInfoDTO target) {
        return this.advistoryInfoDTOMapper.insertSelective(target) > 0;
    }


    @Override
    public AdvistoryInfoDTO createAndGet(AdvistoryInfoDTO target) {
        AdvistoryInfoDTO result = null;
        if (this.create(target)) {
            result = this.getById(target.getAdvistoryId());
        }
        return result;
    }

    @Override
    public boolean update(AdvistoryInfoDTO target) {
        target.setUpdateTime(new Date());
        return this.advistoryInfoDTOMapper.updateByPrimaryKeySelective(target) > 0;
    }

    @Override
    public AdvistoryInfoDTO updateAndGet(AdvistoryInfoDTO target) {
        AdvistoryInfoDTO result = null;
        if (this.update(target)) {
            result = this.getById(target.getAdvistoryId());
        }
        return result;
    }

    @Override
    public boolean remove(AdvistoryInfoDTO target) {
        target.setDeleted(EnumDeleted.DELETED);
        return this.update(target);
    }

    @Override
    public boolean batchCreate(List<AdvistoryInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::create).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchUpdate(List<AdvistoryInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::update).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchRemove(List<AdvistoryInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::remove).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean addReadNumber(Long advistoryId) {
        return this.advistoryInfoDTOMapper.addReadNumber(advistoryId) > 0;
    }

    @Override
    public boolean addFavoriteNumber(Long advistoryId) {
        return this.advistoryInfoDTOMapper.addFavoriteNumber(advistoryId) > 0;
    }

    @Override
    public boolean subFavoriteNumber(Long advistoryId) {
        return this.advistoryInfoDTOMapper.subFavoriteNumber(advistoryId) > 0;
    }

    private void loadDefaultOrder(AdvistoryInfoDTOExample example) {
        if (StringUtils.isEmpty(example.getOrderByClause())) {
            String orderBySql = "create_time" + " " + EnumOrder.DESC.getCode();
            example.setOrderByClause(orderBySql);
        }
    }

    private AdvistoryInfoDTOExample buildQueryExample(AdvistoryInfoQuery query) {
        AdvistoryInfoDTOExample example = new AdvistoryInfoDTOExample();
        AdvistoryInfoDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        String orderBySql = super.buildSortSql(query.buildSortFields());
        example.setOrderByClause(orderBySql);
        example.limit(query.getOffset(), query.getLimit());
        return example;
    }


}
