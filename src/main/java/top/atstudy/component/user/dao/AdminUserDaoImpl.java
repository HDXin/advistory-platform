package top.atstudy.component.user.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.component.base.BaseDao;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumOrder;
import top.atstudy.component.user.dao.dto.AdminUserDTO;
import top.atstudy.component.user.dao.dto.AdminUserDTOExample;
import top.atstudy.component.user.dao.mapper.AdminUserDTOMapper;
import top.atstudy.component.user.vo.req.AdminUserQuery;

import java.util.Date;
import java.util.List;

/**
 * IAdminUserDao 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class AdminUserDaoImpl extends BaseDao implements IAdminUserDao {
    /******* Fields Area *******/
    private AdminUserDTOMapper adminUserDTOMapper;

    /******* Construction Area *******/
    public AdminUserDaoImpl(@Autowired AdminUserDTOMapper adminUserDTOMapper) {
        this.adminUserDTOMapper = adminUserDTOMapper;
    }

    @Override
    public AdminUserDTO getById(Long id) {
        return this.adminUserDTOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<AdminUserDTO> findByExample(AdminUserDTOExample example, Pagination... paginationParam) {
        Pagination pagination = paginationParam == null || paginationParam.length == 0 ? new Pagination() : paginationParam[0];
        Page<AdminUserDTO> page = new Page<AdminUserDTO>(pagination);
        String orderBySql = super.buildSortSql(page.buildSortFields());
        example.setOrderByClause(orderBySql);
        long total = this.adminUserDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<AdminUserDTO> targets = this.adminUserDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }


    @Override
    public List<AdminUserDTO> listByExample(AdminUserDTOExample example) {
        this.loadDefaultOrder(example);
        return this.adminUserDTOMapper.selectByExample(example);
    }

    @Override
    public AdminUserDTO getByExample(AdminUserDTOExample example) {
        AdminUserDTO target = null;
        this.loadDefaultOrder(example);
        List<AdminUserDTO> targets = this.adminUserDTOMapper.selectByExample(example);
        if (targets.size() > 0) {
            target = targets.get(0);
        }
        return target;
    }

    @Override
    public Long countByExample(AdminUserDTOExample example) {
        this.loadDefaultOrder(example);
        return this.adminUserDTOMapper.countByExample(example);
    }

    @Override
    public Page<AdminUserDTO> findByQuery(AdminUserQuery query) {
        Page<AdminUserDTO> page = new Page<>(query);
        AdminUserDTOExample example = this.buildQueryExample(query);

        this.loadDefaultOrder(example);
        long total = this.adminUserDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<AdminUserDTO> targets = this.adminUserDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }

    @Override
    public List<AdminUserDTO> listByQuery(AdminUserQuery query) {
        AdminUserDTOExample example = this.buildQueryExample(query);
        return this.adminUserDTOMapper.selectByExample(example);
    }


    @Override
    public Long countByQuery(AdminUserQuery query) {
        AdminUserDTOExample example = this.buildQueryExample(query);
        return this.adminUserDTOMapper.countByExample(example);
    }

    @Override
    public boolean create(AdminUserDTO target) {
        return this.adminUserDTOMapper.insertSelective(target) > 0;
    }


    @Override
    public AdminUserDTO createAndGet(AdminUserDTO target) {
        AdminUserDTO result = null;
        if (this.create(target)) {
            result = this.getById(target.getUserId());
        }
        return result;
    }

    @Override
    public boolean update(AdminUserDTO target) {
        target.setUpdateTime(new Date());
        return this.adminUserDTOMapper.updateByPrimaryKeySelective(target) > 0;
    }

    @Override
    public AdminUserDTO updateAndGet(AdminUserDTO target) {
        AdminUserDTO result = null;
        if (this.update(target)) {
            result = this.getById(target.getUserId());
        }
        return result;
    }

    @Override
    public boolean remove(AdminUserDTO target) {
        target.setDeleted(EnumDeleted.DELETED);
        return this.update(target);
    }

    @Override
    public boolean batchCreate(List<AdminUserDTO> targets) {
        boolean batchFlag = targets.stream().map(this::create).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchUpdate(List<AdminUserDTO> targets) {
        boolean batchFlag = targets.stream().map(this::update).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchRemove(List<AdminUserDTO> targets) {
        boolean batchFlag = targets.stream().map(this::remove).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    private void loadDefaultOrder(AdminUserDTOExample example) {
        if (StringUtils.isEmpty(example.getOrderByClause())) {
            String orderBySql = "create_time" + " " + EnumOrder.DESC.getCode();
            example.setOrderByClause(orderBySql);
        }
    }

    private AdminUserDTOExample buildQueryExample(AdminUserQuery query) {
        AdminUserDTOExample example = new AdminUserDTOExample();
        AdminUserDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        String orderBySql = super.buildSortSql(query.buildSortFields());
        example.setOrderByClause(orderBySql);
        example.limit(query.getOffset(), query.getLimit());
        return example;
    }


}
