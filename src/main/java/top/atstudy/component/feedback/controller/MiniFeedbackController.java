package top.atstudy.component.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.component.base.controller.BasicAppController;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.feedback.service.IFeedbackService;
import top.atstudy.component.feedback.vo.req.FeedbackReq;
import top.atstudy.component.feedback.vo.resp.FeedbackResp;

@RestController
@RequestMapping("/api/mini/feedback")
public class MiniFeedbackController extends BasicAppController {
    /******* Fields Area *******/

    @Autowired
    private IFeedbackService feedbackService;

    /******* Construction Area *******/
    /******* GetSet Area ******/

    /**
     * 用户意见返回
     * @param req
     * @return
     */
    @PostMapping("")
    public FeedbackResp create(@RequestBody FeedbackReq req) throws APIException {
        return this.feedbackService.createAndGet(req, getSessionUser());
    }
}

