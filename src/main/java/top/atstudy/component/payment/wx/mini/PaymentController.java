package top.atstudy.component.payment.wx.mini;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.atstudy.component.auth.vo.AppAuthVo;
import top.atstudy.component.base.controller.BasicAppController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-25
 * Time: 17:07
 */
@RestController
@RequestMapping("/api/mini/payment")
public class PaymentController extends BasicAppController{

    @PostMapping("/notify")
    public String notify(@RequestBody AppAuthVo appAuthVo){


        return "SUCCESS";
    }

}
