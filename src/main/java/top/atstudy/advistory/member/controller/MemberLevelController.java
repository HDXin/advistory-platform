package top.atstudy.advistory.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.atstudy.advistory.member.service.IMemberLevelService;
import top.atstudy.advistory.member.vo.req.MemberLevelQuery;
import top.atstudy.advistory.member.vo.req.MemberLevelReq;
import top.atstudy.advistory.member.vo.resp.MemberLevelResp;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.controller.BasicAdminController;

@RestController
@RequestMapping("/api/admin/memberLevel")
public class MemberLevelController extends BasicAdminController {
    /******* Fields Area *******/

    @Autowired
    private IMemberLevelService memberLevelService;

    /******* Construction Area *******/
    /******* GetSet Area ******/

    /**
     * 编辑会员等级价格信息
     * @param req
     * @return
     */
    @PutMapping("/{memberLevelId}")
    public MemberLevelResp create(@PathVariable("memberLevelId") Long memberLevelId,
                                  @RequestBody MemberLevelReq req){
        req.setMemberLevelId(memberLevelId);
        return this.memberLevelService.update(req, getSessionUser());
    }

    /**
     * 获取会员等级价格信息
     * @param memberLevelId
     * @return
     */
    @GetMapping("/{memberLevelId}")
    public MemberLevelResp get(@PathVariable("memberLevelId") Long memberLevelId) {
        MemberLevelResp target = this.memberLevelService.getById(memberLevelId);
        return target;
    }

    /**
     * 获取会员价格表
     * @param query
     * @return
     */
    @GetMapping("")
    public Page<MemberLevelResp> find(MemberLevelQuery query) {
        Page<MemberLevelResp> target = this.memberLevelService.findByQuery(query);
        return target;
    }

//    @DeleteMapping("/{id}")
//    public void remove(@PathVariable("id") Long id) throws APIException {
//        this.memberLevelService.remove(id, getSessionUser());
//    }

    /******* Method Area *******/


}

