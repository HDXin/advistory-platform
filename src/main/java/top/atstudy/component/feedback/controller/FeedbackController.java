package top.atstudy.component.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicController;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.feedback.service.IFeedbackService;
import top.atstudy.component.feedback.vo.req.FeedbackQuery;
import top.atstudy.component.feedback.vo.resp.FeedbackResp;

/**
 * smart-mybatis-spring-boot-starter
 * <p>
 * Description :
 * <p>
 * Creator :
 *
 * @author Sudao @ Tim Zhang
 * @email : zhanglong@kuaicto.com or solidsnake2007@gmail.com
 * @date: 2017/11/15
 * @time: 下午1:27
 * =========================================
 * <p>
 * Contributors :
 * Tim Zhang - 2017/11/15 下午1:27
 */
@RestController
@RequestMapping("/api/admin/feedback")
public class FeedbackController extends BasicController {
    /******* Fields Area *******/

    @Autowired
    private IFeedbackService feedbackService;

    /******* Construction Area *******/
    /******* GetSet Area ******/


    @GetMapping("/{id}")
    public FeedbackResp get(@PathVariable("id") Long id) {
        FeedbackResp target = this.feedbackService.getById(id);
        return target;
    }

    @GetMapping("")
    public Page<FeedbackResp> find(FeedbackQuery query) {
        Page<FeedbackResp> target = this.feedbackService.findByQuery(query);
        return target;
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) throws APIException {
        this.feedbackService.remove(id, super.getSessionUser());
    }

    /******* Method Area *******/


}

