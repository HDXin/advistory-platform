package top.atstudy.component.feedback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.feedback.dao.IFeedbackDao;
import top.atstudy.component.feedback.dao.dto.FeedbackDTO;
import top.atstudy.component.feedback.dao.dto.FeedbackDTOExample;
import top.atstudy.component.feedback.vo.req.FeedbackQuery;
import top.atstudy.component.feedback.vo.req.FeedbackReq;
import top.atstudy.component.feedback.vo.resp.FeedbackResp;
import java.util.List;

/**
 * IFeedbackService 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class FeedbackServiceImpl implements IFeedbackService {
    /******* Fields Area *******/

    private IFeedbackDao feedbackDao;

    /******* Construction Area *******/
    public FeedbackServiceImpl(@Autowired IFeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

    @Override
    public FeedbackResp getById(Long id) {
        FeedbackResp target = null;
        FeedbackDTOExample example = new FeedbackDTOExample();
        FeedbackDTOExample.Criteria criteria = example.createCriteria();
        criteria.andFeedbackIdEqualTo(id);
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        List<FeedbackDTO> targets = this.feedbackDao.listByExample(example);
        FeedbackDTO targetDto = this.feedbackDao.getByExample(example);
        if (targetDto != null) {
            target = FeedbackResp.parseSinglet(targetDto);
        }
        return target;
    }



    @Override
    public Page<FeedbackResp> findByQuery(FeedbackQuery query) {
        Page<FeedbackDTO> targetPage = this.feedbackDao.findByQuery(query);
        Page<FeedbackResp> page = new Page<>(targetPage);
        page.setItems(FeedbackResp.parseList(targetPage.getItems()));
        return page;
    }

    @Override
    public List<FeedbackResp> listByQuery(FeedbackQuery query) {
        List<FeedbackDTO> targets = this.feedbackDao.listByQuery(query);
        return FeedbackResp.parseList(targets);
    }

    @Override
    public Long countByQuery(FeedbackQuery query) {
        return this.feedbackDao.countByQuery(query);
    }

    @Override
    public FeedbackResp createAndGet(FeedbackReq req, IOperatorAware operator) {
        FeedbackDTO target = req.convertToDTO();
        target.setOperator(operator, true);
        target = this.feedbackDao.createAndGet(target);
        return FeedbackResp.parseSinglet(target);
    }

    @Override
    public FeedbackResp update(FeedbackReq req, IOperatorAware operator) {
        FeedbackDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        target = this.feedbackDao.updateAndGet(target);
        return FeedbackResp.parseSinglet(target);
    }

    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        FeedbackDTO target = this.feedbackDao.getById(id);
        target.setOperator(operator, false);
        return this.feedbackDao.remove(target);
    }
    /******* GetSet Area ******/

    /******* Method Area *******/


}
