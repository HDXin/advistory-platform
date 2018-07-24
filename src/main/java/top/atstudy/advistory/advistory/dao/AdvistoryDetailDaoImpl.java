package top.atstudy.advistory.advistory.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.advistory.advistory.dao.dto.AdvistoryDetailDTO;
import top.atstudy.advistory.advistory.dao.dto.AdvistoryDetailDTOExample;
import top.atstudy.advistory.advistory.dao.mapper.AdvistoryDetailDTOMapper;
import top.atstudy.advistory.advistory.vo.req.AdvistoryDetailQuery;
import top.atstudy.component.base.BaseDao;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.base.SortField;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumOrder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * IAdvistoryDetailDao 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class AdvistoryDetailDaoImpl extends BaseDao implements IAdvistoryDetailDao {
    /******* Fields Area *******/
    private AdvistoryDetailDTOMapper advistoryDetailDTOMapper;

    /******* Construction Area *******/
    public AdvistoryDetailDaoImpl(@Autowired AdvistoryDetailDTOMapper advistoryDetailDTOMapper) {
        this.advistoryDetailDTOMapper = advistoryDetailDTOMapper;
    }

    @Override
    public AdvistoryDetailDTO getById(Long id) {
        return this.advistoryDetailDTOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<AdvistoryDetailDTO> findByExample(AdvistoryDetailDTOExample example, Pagination... paginationParam) {
        Pagination pagination = paginationParam == null || paginationParam.length == 0 ? new Pagination() : paginationParam[0];
        Page<AdvistoryDetailDTO> page = new Page<AdvistoryDetailDTO>(pagination);
        String orderBySql = super.buildSortSql(page.buildSortFields());
        example.setOrderByClause(orderBySql);
        long total = this.advistoryDetailDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<AdvistoryDetailDTO> targets = this.advistoryDetailDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }


    @Override
    public List<AdvistoryDetailDTO> listByExample(AdvistoryDetailDTOExample example) {
        this.loadDefaultOrder(example);
        return this.advistoryDetailDTOMapper.selectByExample(example);
    }

    @Override
    public AdvistoryDetailDTO getByExample(AdvistoryDetailDTOExample example) {
        AdvistoryDetailDTO target = null;
        this.loadDefaultOrder(example);
        List<AdvistoryDetailDTO> targets = this.advistoryDetailDTOMapper.selectByExample(example);
        if (targets.size() > 0) {
            target = targets.get(0);
        }
        return target;
    }

    @Override
    public Long countByExample(AdvistoryDetailDTOExample example) {
        this.loadDefaultOrder(example);
        return this.advistoryDetailDTOMapper.countByExample(example);
    }

    @Override
    public Page<AdvistoryDetailDTO> findByQuery(AdvistoryDetailQuery query) {
        Page<AdvistoryDetailDTO> page = new Page<>(query);
        AdvistoryDetailDTOExample example = this.buildQueryExample(query);

        this.loadDefaultOrder(example);
        long total = this.advistoryDetailDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<AdvistoryDetailDTO> targets = this.advistoryDetailDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }

    @Override
    public List<AdvistoryDetailDTO> listByQuery(AdvistoryDetailQuery query) {
        AdvistoryDetailDTOExample example = this.buildQueryExample(query);
        return this.advistoryDetailDTOMapper.selectByExample(example);
    }


    @Override
    public Long countByQuery(AdvistoryDetailQuery query) {
        AdvistoryDetailDTOExample example = this.buildQueryExample(query);
        return this.advistoryDetailDTOMapper.countByExample(example);
    }

    @Override
    public boolean create(AdvistoryDetailDTO target) {
        return this.advistoryDetailDTOMapper.insertSelective(target) > 0;
    }


    @Override
    public AdvistoryDetailDTO createAndGet(AdvistoryDetailDTO target) {
        AdvistoryDetailDTO result = null;
        if (this.create(target)) {
            result = this.getById(target.getAdvistoryDetailId());
        }
        return result;
    }

    @Override
    public boolean update(AdvistoryDetailDTO target) {
        target.setUpdateTime(new Date());
        return this.advistoryDetailDTOMapper.updateByPrimaryKeySelective(target) > 0;
    }

    @Override
    public AdvistoryDetailDTO updateAndGet(AdvistoryDetailDTO target) {
        AdvistoryDetailDTO result = null;
        if (this.update(target)) {
            result = this.getById(target.getAdvistoryDetailId());
        }
        return result;
    }

    @Override
    public boolean remove(AdvistoryDetailDTO target) {
        target.setDeleted(EnumDeleted.DELETED);
        return this.update(target);
    }

    @Override
    public void deleteByAdvistoryId(Long advistoryId) {

        if(advistoryId == null)
            return ;

        AdvistoryDetailDTOExample example = new AdvistoryDetailDTOExample();
        AdvistoryDetailDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL)
                .andAdvistoryIdEqualTo(advistoryId);

        AdvistoryDetailDTO record = new AdvistoryDetailDTO();
        record.setDeleted(EnumDeleted.DELETED);

        this.advistoryDetailDTOMapper.updateByExampleSelective(record, example);
    }

    @Override
    public boolean batchCreate(List<AdvistoryDetailDTO> targets) {
        boolean batchFlag = targets.stream().map(this::create).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchUpdate(List<AdvistoryDetailDTO> targets) {
        boolean batchFlag = targets.stream().map(this::update).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public List<AdvistoryDetailDTO> getByAdvistoryId(Long advistroyId) {

        if(advistroyId == null)
            return null;

        AdvistoryDetailDTOExample example = new AdvistoryDetailDTOExample();
        AdvistoryDetailDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL)
                .andAdvistoryIdEqualTo(advistroyId);

        List<SortField> sortFields = new ArrayList<>();
        sortFields.add(new SortField("display_order", EnumOrder.ASC));
        example.setOrderByClause(buildSortSql(sortFields));
        return this.advistoryDetailDTOMapper.selectByExample(example);
    }

    @Override
    public boolean batchRemove(List<AdvistoryDetailDTO> targets) {
        boolean batchFlag = targets.stream().map(this::remove).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    private void loadDefaultOrder(AdvistoryDetailDTOExample example) {
        if (StringUtils.isEmpty(example.getOrderByClause())) {
            String orderBySql = "create_time" + " " + EnumOrder.DESC.getCode();
            example.setOrderByClause(orderBySql);
        }
    }

    private AdvistoryDetailDTOExample buildQueryExample(AdvistoryDetailQuery query) {
        AdvistoryDetailDTOExample example = new AdvistoryDetailDTOExample();
        AdvistoryDetailDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        String orderBySql = super.buildSortSql(query.buildSortFields());
        example.setOrderByClause(orderBySql);
        example.limit(query.getOffset(), query.getLimit());
        return example;
    }


}
