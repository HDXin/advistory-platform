package top.atstudy.component.user.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.component.base.BaseDao;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumOrder;
import top.atstudy.component.user.dao.dto.AppUserDTO;
import top.atstudy.component.user.dao.dto.AppUserDTOExample;
import top.atstudy.component.user.dao.mapper.AppUserDTOMapper;
import top.atstudy.component.user.vo.req.AppUserQuery;
import top.atstudy.component.user.vo.resp.AppUserResp;

import java.util.Date;
import java.util.List;

/**
 * IAppUserDao 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class AppUserDaoImpl extends BaseDao implements IAppUserDao {
    /******* Fields Area *******/
    private AppUserDTOMapper appUserDTOMapper;

    /******* Construction Area *******/
    public AppUserDaoImpl(@Autowired AppUserDTOMapper appUserDTOMapper) {
        this.appUserDTOMapper = appUserDTOMapper;
    }

    @Override
    public AppUserDTO getById(Long id) {
        return this.appUserDTOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<AppUserDTO> findByExample(AppUserDTOExample example, Pagination... paginationParam) {
        Pagination pagination = paginationParam == null || paginationParam.length == 0 ? new Pagination() : paginationParam[0];
        Page<AppUserDTO> page = new Page<AppUserDTO>(pagination);
        String orderBySql = super.buildSortSql(page.buildSortFields());
        example.setOrderByClause(orderBySql);
        long total = this.appUserDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<AppUserDTO> targets = this.appUserDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }


    @Override
    public List<AppUserDTO> listByExample(AppUserDTOExample example) {
        this.loadDefaultOrder(example);
        return this.appUserDTOMapper.selectByExample(example);
    }

    @Override
    public AppUserDTO getByExample(AppUserDTOExample example) {
        AppUserDTO target = null;
        this.loadDefaultOrder(example);
        List<AppUserDTO> targets = this.appUserDTOMapper.selectByExample(example);
        if (targets.size() > 0) {
            target = targets.get(0);
        }
        return target;
    }

    @Override
    public Long countByExample(AppUserDTOExample example) {
        this.loadDefaultOrder(example);
        return this.appUserDTOMapper.countByExample(example);
    }

    @Override
    public Page<AppUserDTO> findByQuery(AppUserQuery query) {
        Page<AppUserDTO> page = new Page<>(query);
        AppUserDTOExample example = this.buildQueryExample(query);

        this.loadDefaultOrder(example);
        long total = this.appUserDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<AppUserDTO> targets = this.appUserDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }

    @Override
    public List<AppUserDTO> listByQuery(AppUserQuery query) {
        AppUserDTOExample example = this.buildQueryExample(query);
        return this.appUserDTOMapper.selectByExample(example);
    }


    @Override
    public Long countByQuery(AppUserQuery query) {
        AppUserDTOExample example = this.buildQueryExample(query);
        return this.appUserDTOMapper.countByExample(example);
    }

    @Override
    public boolean create(AppUserDTO target) {
        return this.appUserDTOMapper.insertSelective(target) > 0;
    }


    @Override
    public AppUserDTO createAndGet(AppUserDTO target) {
        AppUserDTO result = null;
        if (this.create(target)) {
            result = this.getById(target.getUserId());
        }
        return result;
    }

    @Override
    public boolean update(AppUserDTO target) {
        target.setUpdateTime(new Date());
        return this.appUserDTOMapper.updateByPrimaryKeySelective(target) > 0;
    }

    @Override
    public AppUserDTO updateAndGet(AppUserDTO target) {
        AppUserDTO result = null;
        if (this.update(target)) {
            result = this.getById(target.getUserId());
        }
        return result;
    }

    @Override
    public AppUserDTO getByOpenid(String openid) {

        if(StringUtils.isBlank(openid))
            return null;

        AppUserDTOExample example = new AppUserDTOExample();
        AppUserDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL)
                .andOpenidEqualTo(openid);

        List<AppUserDTO> list = this.listByExample(example);
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    @Override
    public boolean remove(AppUserDTO target) {
        target.setDeleted(EnumDeleted.DELETED);
        return this.update(target);
    }

    @Override
    public boolean batchCreate(List<AppUserDTO> targets) {
        boolean batchFlag = targets.stream().map(this::create).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchUpdate(List<AppUserDTO> targets) {
        boolean batchFlag = targets.stream().map(this::update).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchRemove(List<AppUserDTO> targets) {
        boolean batchFlag = targets.stream().map(this::remove).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    private void loadDefaultOrder(AppUserDTOExample example) {
        if (StringUtils.isEmpty(example.getOrderByClause())) {
            String orderBySql = "create_time" + " " + EnumOrder.DESC.getCode();
            example.setOrderByClause(orderBySql);
        }
    }

    private AppUserDTOExample buildQueryExample(AppUserQuery query) {
        AppUserDTOExample example = new AppUserDTOExample();
        AppUserDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        String orderBySql = super.buildSortSql(query.buildSortFields());
        example.setOrderByClause(orderBySql);
        example.limit(query.getOffset(), query.getLimit());
        return example;
    }


}
