package top.atstudy.advistory.member.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.advistory.member.dao.dto.MemberLevelDTO;
import top.atstudy.advistory.member.dao.dto.MemberLevelDTOExample;
import top.atstudy.advistory.member.dao.mapper.MemberLevelDTOMapper;
import top.atstudy.advistory.member.vo.req.MemberLevelQuery;
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
 * IMemberLevelDao 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class MemberLevelDaoImpl extends BaseDao implements IMemberLevelDao {
    /******* Fields Area *******/
    private MemberLevelDTOMapper memberLevelDTOMapper;

    /******* Construction Area *******/
    public MemberLevelDaoImpl(@Autowired MemberLevelDTOMapper memberLevelDTOMapper) {
        this.memberLevelDTOMapper = memberLevelDTOMapper;
    }

    @Override
    public MemberLevelDTO getById(Long id) {
        return this.memberLevelDTOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<MemberLevelDTO> findByExample(MemberLevelDTOExample example, Pagination... paginationParam) {
        Pagination pagination = paginationParam == null || paginationParam.length == 0 ? new Pagination() : paginationParam[0];
        Page<MemberLevelDTO> page = new Page<MemberLevelDTO>(pagination);
        String orderBySql = super.buildSortSql(page.buildSortFields());
        example.setOrderByClause(orderBySql);
        long total = this.memberLevelDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<MemberLevelDTO> targets = this.memberLevelDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }


    @Override
    public List<MemberLevelDTO> listByExample(MemberLevelDTOExample example) {
        this.loadDefaultOrder(example);
        return this.memberLevelDTOMapper.selectByExample(example);
    }

    @Override
    public MemberLevelDTO getByExample(MemberLevelDTOExample example) {
        MemberLevelDTO target = null;
        this.loadDefaultOrder(example);
        List<MemberLevelDTO> targets = this.memberLevelDTOMapper.selectByExample(example);
        if (targets.size() > 0) {
            target = targets.get(0);
        }
        return target;
    }

    @Override
    public Long countByExample(MemberLevelDTOExample example) {
        this.loadDefaultOrder(example);
        return this.memberLevelDTOMapper.countByExample(example);
    }

    @Override
    public Page<MemberLevelDTO> findByQuery(MemberLevelQuery query) {
        Page<MemberLevelDTO> page = new Page<>(query);
        MemberLevelDTOExample example = this.buildQueryExample(query);
        this.loadDefaultOrder(example);
        long total = this.memberLevelDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<MemberLevelDTO> targets = this.memberLevelDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }

    @Override
    public List<MemberLevelDTO> listByQuery(MemberLevelQuery query) {
        MemberLevelDTOExample example = this.buildQueryExample(query);
        return this.memberLevelDTOMapper.selectByExample(example);
    }


    @Override
    public Long countByQuery(MemberLevelQuery query) {
        MemberLevelDTOExample example = this.buildQueryExample(query);
        return this.memberLevelDTOMapper.countByExample(example);
    }

    @Override
    public boolean create(MemberLevelDTO target) {
        return this.memberLevelDTOMapper.insertSelective(target) > 0;
    }


    @Override
    public MemberLevelDTO createAndGet(MemberLevelDTO target) {
        MemberLevelDTO result = null;
        if (this.create(target)) {
            result = this.getById(target.getMemberLevelId());
        }
        return result;
    }

    @Override
    public boolean update(MemberLevelDTO target) {
        target.setUpdateTime(new Date());
        return this.memberLevelDTOMapper.updateByPrimaryKeySelective(target) > 0;
    }

    @Override
    public MemberLevelDTO updateAndGet(MemberLevelDTO target) {
        MemberLevelDTO result = null;
        if (this.update(target)) {
            result = this.getById(target.getMemberLevelId());
        }
        return result;
    }

    @Override
    public boolean remove(MemberLevelDTO target) {
        target.setDeleted(EnumDeleted.DELETED);
        return this.update(target);
    }

    @Override
    public boolean batchCreate(List<MemberLevelDTO> targets) {
        boolean batchFlag = targets.stream().map(this::create).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchUpdate(List<MemberLevelDTO> targets) {
        boolean batchFlag = targets.stream().map(this::update).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchRemove(List<MemberLevelDTO> targets) {
        boolean batchFlag = targets.stream().map(this::remove).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    private void loadDefaultOrder(MemberLevelDTOExample example) {
        if (StringUtils.isEmpty(example.getOrderByClause())) {
            String orderBySql = "create_time" + " " + EnumOrder.DESC.getCode();
            example.setOrderByClause(orderBySql);
        }
    }

    private MemberLevelDTOExample buildQueryExample(MemberLevelQuery query) {
        MemberLevelDTOExample example = new MemberLevelDTOExample();
        MemberLevelDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        List<SortField> sortFields = new ArrayList<>();
        sortFields.add(new SortField("order_number", EnumOrder.ASC));
        sortFields.add(new SortField("final_price", EnumOrder.ASC));
        String orderBySql = super.buildSortSql(sortFields);
        example.setOrderByClause(orderBySql);
        example.limit(query.getOffset(), query.getLimit());
        return example;
    }


}
