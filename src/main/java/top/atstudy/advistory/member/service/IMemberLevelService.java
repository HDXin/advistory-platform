package top.atstudy.advistory.member.service;

import top.atstudy.advistory.member.vo.req.MemberLevelQuery;
import top.atstudy.advistory.member.vo.req.MemberLevelReq;
import top.atstudy.advistory.member.vo.resp.MemberLevelResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;

import java.util.List;

/**
 * IMemberLevelService 接口
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IMemberLevelService {

    MemberLevelResp getById(Long id);

    Page<MemberLevelResp> findByQuery(MemberLevelQuery query);

    List<MemberLevelResp> listByQuery(MemberLevelQuery query);

    Long countByQuery(MemberLevelQuery query);

    MemberLevelResp createAndGet(MemberLevelReq req, IOperatorAware operator);

    MemberLevelResp update(MemberLevelReq req, IOperatorAware operator);

    boolean remove(Long id, IOperatorAware operator);


}

