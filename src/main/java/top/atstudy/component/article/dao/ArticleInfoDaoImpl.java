package top.atstudy.component.article.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.component.article.dao.dto.ArticleInfoDTO;
import top.atstudy.component.article.dao.dto.ArticleInfoDTOExample;
import top.atstudy.component.article.dao.mapper.ArticleInfoDTOMapper;
import top.atstudy.component.article.vo.req.ArticleInfoQuery;
import top.atstudy.component.base.BaseDao;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.Pagination;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumOrder;

import java.util.Date;
import java.util.List;

/**
 * IArticleInfoDao 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class ArticleInfoDaoImpl extends BaseDao implements IArticleInfoDao {
    /******* Fields Area *******/
    private ArticleInfoDTOMapper articleInfoDTOMapper;

    /******* Construction Area *******/
    public ArticleInfoDaoImpl(@Autowired ArticleInfoDTOMapper articleInfoDTOMapper) {
        this.articleInfoDTOMapper = articleInfoDTOMapper;
    }

    @Override
    public ArticleInfoDTO getById(Long id) {
        return this.articleInfoDTOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<ArticleInfoDTO> findByExample(ArticleInfoDTOExample example, Pagination... paginationParam) {
        Pagination pagination = paginationParam == null || paginationParam.length == 0 ? new Pagination() : paginationParam[0];
        Page<ArticleInfoDTO> page = new Page<ArticleInfoDTO>(pagination);
        String orderBySql = super.buildSortSql(page.buildSortFields());
        example.setOrderByClause(orderBySql);
        long total = this.articleInfoDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<ArticleInfoDTO> targets = this.articleInfoDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }


    @Override
    public List<ArticleInfoDTO> listByExample(ArticleInfoDTOExample example) {
        this.loadDefaultOrder(example);
        return this.articleInfoDTOMapper.selectByExample(example);
    }

    @Override
    public ArticleInfoDTO getByExample(ArticleInfoDTOExample example) {
        ArticleInfoDTO target = null;
        this.loadDefaultOrder(example);
        List<ArticleInfoDTO> targets = this.articleInfoDTOMapper.selectByExample(example);
        if (targets.size() > 0) {
            target = targets.get(0);
        }
        return target;
    }

    @Override
    public Long countByExample(ArticleInfoDTOExample example) {
        this.loadDefaultOrder(example);
        return this.articleInfoDTOMapper.countByExample(example);
    }

    @Override
    public Page<ArticleInfoDTO> findByQuery(ArticleInfoQuery query) {
        Page<ArticleInfoDTO> page = new Page<>(query);
        ArticleInfoDTOExample example = this.buildQueryExample(query);

        this.loadDefaultOrder(example);
        long total = this.articleInfoDTOMapper.countByExample(example);
        page.setTotal(total);
        if (total > page.getOffset()) {
            example.limit(page.getOffset(), page.getLimit());
            List<ArticleInfoDTO> targets = this.articleInfoDTOMapper.selectByExample(example);
            page.setItems(targets);
        }
        return page;
    }

    @Override
    public List<ArticleInfoDTO> listByQuery(ArticleInfoQuery query) {
        ArticleInfoDTOExample example = this.buildQueryExample(query);
        return this.articleInfoDTOMapper.selectByExample(example);
    }


    @Override
    public Long countByQuery(ArticleInfoQuery query) {
        ArticleInfoDTOExample example = this.buildQueryExample(query);
        return this.articleInfoDTOMapper.countByExample(example);
    }

    @Override
    public boolean create(ArticleInfoDTO target) {
        return this.articleInfoDTOMapper.insertSelective(target) > 0;
    }


    @Override
    public ArticleInfoDTO createAndGet(ArticleInfoDTO target) {
        ArticleInfoDTO result = null;
        if (this.create(target)) {
            result = this.getById(target.getArticleId());
        }
        return result;
    }

    @Override
    public boolean update(ArticleInfoDTO target) {
        target.setUpdateTime(new Date());
        return this.articleInfoDTOMapper.updateByPrimaryKeySelective(target) > 0;
    }

    @Override
    public ArticleInfoDTO updateAndGet(ArticleInfoDTO target) {
        ArticleInfoDTO result = null;
        if (this.update(target)) {
            result = this.getById(target.getArticleId());
        }
        return result;
    }

    @Override
    public boolean remove(ArticleInfoDTO target) {
        target.setDeleted(EnumDeleted.DELETED);
        return this.update(target);
    }

    @Override
    public boolean batchCreate(List<ArticleInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::create).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchUpdate(List<ArticleInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::update).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    @Override
    public boolean batchRemove(List<ArticleInfoDTO> targets) {
        boolean batchFlag = targets.stream().map(this::remove).filter(v -> !v).count() == 0;
        return batchFlag;
    }

    private void loadDefaultOrder(ArticleInfoDTOExample example) {
        if (StringUtils.isEmpty(example.getOrderByClause())) {
            String orderBySql = "create_time" + " " + EnumOrder.DESC.getCode();
            example.setOrderByClause(orderBySql);
        }
    }

    private ArticleInfoDTOExample buildQueryExample(ArticleInfoQuery query) {
        ArticleInfoDTOExample example = new ArticleInfoDTOExample();
        ArticleInfoDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        String orderBySql = super.buildSortSql(query.buildSortFields());
        example.setOrderByClause(orderBySql);
        example.limit(query.getOffset(), query.getLimit());
        return example;
    }


}
