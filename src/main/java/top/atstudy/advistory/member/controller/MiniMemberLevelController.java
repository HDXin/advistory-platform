package top.atstudy.advistory.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.member.service.IMemberLevelService;
import top.atstudy.advistory.member.vo.req.MemberLevelQuery;
import top.atstudy.advistory.member.vo.resp.MemberLevelResp;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicAdminController;
import java.util.List;

@RestController
@RequestMapping("/api/mini/memberLevel")
public class MiniMemberLevelController extends BasicAdminController {
    /******* Fields Area *******/

    @Autowired
    private IMemberLevelService memberLevelService;

    /******* Construction Area *******/
    /******* GetSet Area ******/


    /**
     * 获取会员价格表
     * @param query
     * @return
     */
    @GetMapping("")
    public List<MemberLevelResp> find(MemberLevelQuery query) {
        Page<MemberLevelResp> target = this.memberLevelService.findByQuery(query);
        return target.getItems();
    }

}

