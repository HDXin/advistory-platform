package top.atstudy.component.feedback.service;

import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.feedback.vo.req.FeedbackQuery;
import top.atstudy.component.feedback.vo.req.FeedbackReq;
import top.atstudy.component.feedback.vo.resp.FeedbackResp;

import java.util.List;

/**
 * IFeedbackService 接口
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IFeedbackService {

    FeedbackResp getById(Long id);

    Page<FeedbackResp> findByQuery(FeedbackQuery query);

    List<FeedbackResp> listByQuery(FeedbackQuery query);

    Long countByQuery(FeedbackQuery query);

    FeedbackResp createAndGet(FeedbackReq req, IOperatorAware operator);

    FeedbackResp update(FeedbackReq req, IOperatorAware operator);

    boolean remove(Long id, IOperatorAware operator);


}

