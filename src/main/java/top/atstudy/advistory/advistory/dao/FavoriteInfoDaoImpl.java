package top.atstudy.advistory.advistory.dao;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.advistory.advistory.dao.dto.FavoriteInfoDTO;
import top.atstudy.advistory.advistory.dao.dto.FavoriteInfoDTOExample;
import top.atstudy.advistory.advistory.dao.mapper.FavoriteInfoDTOMapper;
import top.atstudy.advistory.advistory.vo.req.FavoriteInfoQuery;
import top.atstudy.component.base.BaseDao;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumFavoriteStatus;
import top.atstudy.component.enums.EnumOrder;
import top.atstudy.component.enums.EnumRelationType;

import java.util.Date;
import java.util.List;

/**
 * IFavoriteInfoDao 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class FavoriteInfoDaoImpl extends BaseDao implements IFavoriteInfoDao {
    /******* Fields Area *******/
    private FavoriteInfoDTOMapper favoriteInfoDTOMapper;

    /******* Construction Area *******/
    public FavoriteInfoDaoImpl(@Autowired FavoriteInfoDTOMapper favoriteInfoDTOMapper) {
        this.favoriteInfoDTOMapper = favoriteInfoDTOMapper;
    }

    @Override
    public FavoriteInfoDTO getById(Long id) {
        return this.favoriteInfoDTOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<FavoriteInfoDTO> findByExample(FavoriteInfoDTOExample example, Pagination... paginationParam) {
        Pagination pagination = paginationParam == null || paginationParam.length == 0 ? new Pagination() : paginationParam[0];
        Page<FavoriteInfoDTO> page = new Page<FavoriteInfoDTO>(pagination);
        String orderBySql = super.buildSortSql(page.buildSortFields());
        example.setOrderByClause(orderBySql);
        long total = this.favoriteInfoDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<FavoriteInfoDTO> targets = this.favoriteInfoDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }


    @Override
    public List<FavoriteInfoDTO> listByExample(FavoriteInfoDTOExample example) {
        this.loadDefaultOrder(example);
        return this.favoriteInfoDTOMapper.selectByExample(example);
    }

    @Override
    public FavoriteInfoDTO getByExample(FavoriteInfoDTOExample example) {
        FavoriteInfoDTO target = null;
        this.loadDefaultOrder(example);
        List<FavoriteInfoDTO> targets = this.favoriteInfoDTOMapper.selectByExample(example);
        if (targets.size() > 0) {
            target = targets.get(0);
        }
        return target;
    }

    @Override
    public Long countByExample(FavoriteInfoDTOExample example) {
        this.loadDefaultOrder(example);
        return this.favoriteInfoDTOMapper.countByExample(example);
    }

    @Override
    public Page<FavoriteInfoDTO> findByQuery(FavoriteInfoQuery query) {
        Page<FavoriteInfoDTO> page = new Page<>(query);
        FavoriteInfoDTOExample example = this.buildQueryExample(query);

        this.loadDefaultOrder(example);
        long total = this.favoriteInfoDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<FavoriteInfoDTO> targets = this.favoriteInfoDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }

    @Override
    public List<FavoriteInfoDTO> listByQuery(FavoriteInfoQuery query) {
        FavoriteInfoDTOExample example = this.buildQueryExample(query);
        return this.favoriteInfoDTOMapper.selectByExample(example);
    }


    @Override
    public Long countByQuery(FavoriteInfoQuery query) {
        FavoriteInfoDTOExample example = this.buildQueryExample(query);
        return this.favoriteInfoDTOMapper.countByExample(example);
    }

    @Override
    public boolean create(FavoriteInfoDTO target) {
        return this.favoriteInfoDTOMapper.insertSelective(target) > 0;
    }


    @Override
    public FavoriteInfoDTO createAndGet(FavoriteInfoDTO target) {
        FavoriteInfoDTO result = null;
        if (this.create(target)) {
            result = this.getById(target.getFavoriteId());
        }
        return result;
    }

    @Override
    public boolean update(FavoriteInfoDTO target) {
        target.setUpdateTime(new Date());
        return this.favoriteInfoDTOMapper.updateByPrimaryKeySelective(target) > 0;
    }

    @Override
    public FavoriteInfoDTO updateAndGet(FavoriteInfoDTO target) {
        FavoriteInfoDTO result = null;
        if (this.update(target)) {
            result = this.getById(target.getFavoriteId());
        }
        return result;
    }

    @Override
    public boolean remove(FavoriteInfoDTO target) {
        target.setDeleted(EnumDeleted.DELETED);
        return this.update(target);
    }

    @Override
    public boolean batchCreate(List<FavoriteInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::create).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchUpdate(List<FavoriteInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::update).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchRemove(List<FavoriteInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::remove).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public FavoriteInfoDTO getByRelationIdAndType(Long relationId, EnumRelationType relationType, Long userId, Boolean valid) {

        if(relationId == null
                || relationType == null
                || userId == null)
            return null;

        FavoriteInfoDTOExample example = new FavoriteInfoDTOExample();
        FavoriteInfoDTOExample.Criteria criteria = example.createCriteria();

        //默认查询所有收藏
        criteria.andRelationIdEqualTo(relationId)
                .andRelationTypeEqualTo(relationType)
                .andUserIdEqualTo(userId);

        //查询有效收藏
        if(valid == null || valid){
            criteria.andDeletedEqualTo(EnumDeleted.NORMAL)
                    .andEnableEqualTo(true)
                    .andFavoriteStatusEqualTo(EnumFavoriteStatus.ADD_FAVORITE);
        }

        List<FavoriteInfoDTO> list = this.favoriteInfoDTOMapper.selectByExample(example);
        return CollectionUtils.isEmpty(list) ? null:list.get(0);
    }

    private void loadDefaultOrder(FavoriteInfoDTOExample example) {
        if (StringUtils.isEmpty(example.getOrderByClause())) {
            String orderBySql = "create_time" + " " + EnumOrder.DESC.getCode();
            example.setOrderByClause(orderBySql);
        }
    }

    private FavoriteInfoDTOExample buildQueryExample(FavoriteInfoQuery query) {
        FavoriteInfoDTOExample example = new FavoriteInfoDTOExample();
        FavoriteInfoDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        String orderBySql = super.buildSortSql(query.buildSortFields());
        example.setOrderByClause(orderBySql);
        example.limit(query.getOffset(), query.getLimit());
        return example;
    }


}
