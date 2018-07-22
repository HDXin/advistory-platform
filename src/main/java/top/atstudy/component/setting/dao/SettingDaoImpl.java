package top.atstudy.component.setting.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.component.base.BaseDao;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumOrder;
import top.atstudy.component.setting.dao.dto.SettingDTO;
import top.atstudy.component.setting.dao.dto.SettingDTOExample;
import top.atstudy.component.setting.dao.mapper.SettingDTOMapper;
import top.atstudy.component.setting.vo.req.SettingQuery;
import java.util.Date;
import java.util.List;

/**
 * ISettingDao 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class SettingDaoImpl extends BaseDao implements ISettingDao {
    /******* Fields Area *******/
    private SettingDTOMapper settingDTOMapper;

    /******* Construction Area *******/
    public SettingDaoImpl(@Autowired SettingDTOMapper settingDTOMapper) {
        this.settingDTOMapper = settingDTOMapper;
    }

    @Override
    public SettingDTO getById(Long id) {
        return this.settingDTOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<SettingDTO> findByExample(SettingDTOExample example, Pagination... paginationParam) {
        Pagination pagination = paginationParam == null || paginationParam.length == 0 ? new Pagination() : paginationParam[0];
        Page<SettingDTO> page = new Page<SettingDTO>(pagination);
        String orderBySql = super.buildSortSql(page.buildSortFields());
        example.setOrderByClause(orderBySql);
        long total = this.settingDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<SettingDTO> targets = this.settingDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }


    @Override
    public List<SettingDTO> listByExample(SettingDTOExample example) {
        this.loadDefaultOrder(example);
        return this.settingDTOMapper.selectByExample(example);
    }

    @Override
    public SettingDTO getByExample(SettingDTOExample example) {
        SettingDTO target = null;
        this.loadDefaultOrder(example);
        List<SettingDTO> targets = this.settingDTOMapper.selectByExample(example);
        if (targets.size() > 0) {
            target = targets.get(0);
        }
        return target;
    }

    @Override
    public Long countByExample(SettingDTOExample example) {
        this.loadDefaultOrder(example);
        return this.settingDTOMapper.countByExample(example);
    }

    @Override
    public Page<SettingDTO> findByQuery(SettingQuery query) {
        Page<SettingDTO> page = new Page<>(query);
        SettingDTOExample example = this.buildQueryExample(query);

        this.loadDefaultOrder(example);
        long total = this.settingDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<SettingDTO> targets = this.settingDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }

    @Override
    public List<SettingDTO> listByQuery(SettingQuery query) {
        SettingDTOExample example = this.buildQueryExample(query);
        return this.settingDTOMapper.selectByExample(example);
    }


    @Override
    public Long countByQuery(SettingQuery query) {
        SettingDTOExample example = this.buildQueryExample(query);
        return this.settingDTOMapper.countByExample(example);
    }

    @Override
    public boolean create(SettingDTO target) {
        return this.settingDTOMapper.insertSelective(target) > 0;
    }


    @Override
    public SettingDTO createAndGet(SettingDTO target) {
        SettingDTO result = null;
        if (this.create(target)) {
            result = this.getById(target.getSettingId());
        }
        return result;
    }

    @Override
    public boolean update(SettingDTO target) {
        target.setUpdateTime(new Date());
        return this.settingDTOMapper.updateByPrimaryKeySelective(target) > 0;
    }

    @Override
    public SettingDTO updateAndGet(SettingDTO target) {
        SettingDTO result = null;
        if (this.update(target)) {
            result = this.getById(target.getSettingId());
        }
        return result;
    }

    @Override
    public boolean remove(SettingDTO target) {
        target.setDeleted(EnumDeleted.DELETED);
        return this.update(target);
    }

    @Override
    public boolean batchCreate(List<SettingDTO> targets) {
        boolean batchFlag = targets.stream().map(this::create).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchUpdate(List<SettingDTO> targets) {
        boolean batchFlag = targets.stream().map(this::update).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchRemove(List<SettingDTO> targets) {
        boolean batchFlag = targets.stream().map(this::remove).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    private void loadDefaultOrder(SettingDTOExample example) {
        if (StringUtils.isEmpty(example.getOrderByClause())) {
            String orderBySql = "create_time" + " " + EnumOrder.DESC.getCode();
            example.setOrderByClause(orderBySql);
        }
    }

    private SettingDTOExample buildQueryExample(SettingQuery query) {
        SettingDTOExample example = new SettingDTOExample();
        SettingDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        String orderBySql = super.buildSortSql(query.buildSortFields());
        example.setOrderByClause(orderBySql);
        example.limit(query.getOffset(), query.getLimit());
        return example;
    }


}
