package top.atstudy.component.article.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.component.article.dao.IArticleInfoDao;
import top.atstudy.component.article.dao.dto.ArticleInfoDTO;
import top.atstudy.component.article.dao.dto.ArticleInfoDTOExample;
import top.atstudy.component.article.vo.req.ArticleInfoQuery;
import top.atstudy.component.article.vo.req.ArticleInfoReq;
import top.atstudy.component.article.vo.resp.ArticleInfoResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.enums.EnumDeleted;

import java.util.List;

/**
 * IArticleInfoService 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class ArticleInfoServiceImpl implements IArticleInfoService {
    /******* Fields Area *******/

    private IArticleInfoDao articleInfoDao;

    /******* Construction Area *******/
    public ArticleInfoServiceImpl(@Autowired IArticleInfoDao articleInfoDao) {
        this.articleInfoDao = articleInfoDao;
    }

    @Override
    public ArticleInfoResp getById(Long id) {
        ArticleInfoResp target = null;
        ArticleInfoDTOExample example = new ArticleInfoDTOExample();
        ArticleInfoDTOExample.Criteria criteria = example.createCriteria();
        criteria.andArticleIdEqualTo(id);
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        List<ArticleInfoDTO> targets = this.articleInfoDao.listByExample(example);
        ArticleInfoDTO targetDto = this.articleInfoDao.getByExample(example);
        if (targetDto != null) {
            target = ArticleInfoResp.parseSinglet(targetDto);
        }
        return target;
    }



    @Override
    public Page<ArticleInfoResp> findByQuery(ArticleInfoQuery query) {
        Page<ArticleInfoDTO> targetPage = this.articleInfoDao.findByQuery(query);
        Page<ArticleInfoResp> page = new Page<>(targetPage);
        page.setItems(ArticleInfoResp.parseList(targetPage.getItems()));
        return page;
    }

    @Override
    public List<ArticleInfoResp> listByQuery(ArticleInfoQuery query) {
        List<ArticleInfoDTO> targets = this.articleInfoDao.listByQuery(query);
        return ArticleInfoResp.parseList(targets);
    }

    @Override
    public Long countByQuery(ArticleInfoQuery query) {
        return this.articleInfoDao.countByQuery(query);
    }

    @Override
    public ArticleInfoResp createAndGet(ArticleInfoReq req, IOperatorAware operator) {
        ArticleInfoDTO target = req.convertToDTO();
        target.setOperator(operator, true);
        target = this.articleInfoDao.createAndGet(target);
        return ArticleInfoResp.parseSinglet(target);
    }

    @Override
    public ArticleInfoResp update(ArticleInfoReq req, IOperatorAware operator) {
        ArticleInfoDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        target = this.articleInfoDao.updateAndGet(target);
        return ArticleInfoResp.parseSinglet(target);
    }

    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        ArticleInfoDTO target = this.articleInfoDao.getById(id);
        target.setOperator(operator, false);
        return this.articleInfoDao.remove(target);
    }
    /******* GetSet Area ******/

    /******* Method Area *******/


}
