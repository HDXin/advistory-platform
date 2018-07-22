package top.atstudy.component.feedback.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.component.base.BaseDao;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumOrder;
import top.atstudy.component.feedback.dao.dto.FeedbackDTO;
import top.atstudy.component.feedback.dao.dto.FeedbackDTOExample;
import top.atstudy.component.feedback.dao.mapper.FeedbackDTOMapper;
import top.atstudy.component.feedback.vo.req.FeedbackQuery;
import java.util.Date;
import java.util.List;

/**
 * IFeedbackDao 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class FeedbackDaoImpl extends BaseDao implements IFeedbackDao {
    /******* Fields Area *******/
    private FeedbackDTOMapper feedbackDTOMapper;

    /******* Construction Area *******/
    public FeedbackDaoImpl(@Autowired FeedbackDTOMapper feedbackDTOMapper) {
        this.feedbackDTOMapper = feedbackDTOMapper;
    }

    @Override
    public FeedbackDTO getById(Long id) {
        return this.feedbackDTOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<FeedbackDTO> findByExample(FeedbackDTOExample example, Pagination... paginationParam) {
        Pagination pagination = paginationParam == null || paginationParam.length == 0 ? new Pagination() : paginationParam[0];
        Page<FeedbackDTO> page = new Page<FeedbackDTO>(pagination);
        String orderBySql = super.buildSortSql(page.buildSortFields());
        example.setOrderByClause(orderBySql);
        long total = this.feedbackDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<FeedbackDTO> targets = this.feedbackDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }


    @Override
    public List<FeedbackDTO> listByExample(FeedbackDTOExample example) {
        this.loadDefaultOrder(example);
        return this.feedbackDTOMapper.selectByExample(example);
    }

    @Override
    public FeedbackDTO getByExample(FeedbackDTOExample example) {
        FeedbackDTO target = null;
        this.loadDefaultOrder(example);
        List<FeedbackDTO> targets = this.feedbackDTOMapper.selectByExample(example);
        if (targets.size() > 0) {
            target = targets.get(0);
        }
        return target;
    }

    @Override
    public Long countByExample(FeedbackDTOExample example) {
        this.loadDefaultOrder(example);
        return this.feedbackDTOMapper.countByExample(example);
    }

    @Override
    public Page<FeedbackDTO> findByQuery(FeedbackQuery query) {
        Page<FeedbackDTO> page = new Page<>(query);
        FeedbackDTOExample example = this.buildQueryExample(query);

        this.loadDefaultOrder(example);
        long total = this.feedbackDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<FeedbackDTO> targets = this.feedbackDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }

    @Override
    public List<FeedbackDTO> listByQuery(FeedbackQuery query) {
        FeedbackDTOExample example = this.buildQueryExample(query);
        return this.feedbackDTOMapper.selectByExample(example);
    }


    @Override
    public Long countByQuery(FeedbackQuery query) {
        FeedbackDTOExample example = this.buildQueryExample(query);
        return this.feedbackDTOMapper.countByExample(example);
    }

    @Override
    public boolean create(FeedbackDTO target) {
        return this.feedbackDTOMapper.insertSelective(target) > 0;
    }


    @Override
    public FeedbackDTO createAndGet(FeedbackDTO target) {
        FeedbackDTO result = null;
        if (this.create(target)) {
            result = this.getById(target.getFeedbackId());
        }
        return result;
    }

    @Override
    public boolean update(FeedbackDTO target) {
        target.setUpdateTime(new Date());
        return this.feedbackDTOMapper.updateByPrimaryKeySelective(target) > 0;
    }

    @Override
    public FeedbackDTO updateAndGet(FeedbackDTO target) {
        FeedbackDTO result = null;
        if (this.update(target)) {
            result = this.getById(target.getFeedbackId());
        }
        return result;
    }

    @Override
    public boolean remove(FeedbackDTO target) {
        target.setDeleted(EnumDeleted.DELETED);
        return this.update(target);
    }

    @Override
    public boolean batchCreate(List<FeedbackDTO> targets) {
        boolean batchFlag = targets.stream().map(this::create).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchUpdate(List<FeedbackDTO> targets) {
        boolean batchFlag = targets.stream().map(this::update).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchRemove(List<FeedbackDTO> targets) {
        boolean batchFlag = targets.stream().map(this::remove).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    private void loadDefaultOrder(FeedbackDTOExample example) {
        if (StringUtils.isEmpty(example.getOrderByClause())) {
            String orderBySql = "create_time" + " " + EnumOrder.DESC.getCode();
            example.setOrderByClause(orderBySql);
        }
    }

    private FeedbackDTOExample buildQueryExample(FeedbackQuery query) {
        FeedbackDTOExample example = new FeedbackDTOExample();
        FeedbackDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        String orderBySql = super.buildSortSql(query.buildSortFields());
        example.setOrderByClause(orderBySql);
        example.limit(query.getOffset(), query.getLimit());
        return example;
    }


}
