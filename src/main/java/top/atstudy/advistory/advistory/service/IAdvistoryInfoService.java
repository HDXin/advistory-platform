package top.atstudy.advistory.advistory.service;


import top.atstudy.advistory.advistory.vo.req.AdvistoryInfoQuery;
import top.atstudy.advistory.advistory.vo.req.AdvistoryInfoReq;
import top.atstudy.advistory.advistory.vo.resp.AdvistoryInfoResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;

import java.util.List;

/**
 * IAdvistoryInfoService 接口
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IAdvistoryInfoService {

    AdvistoryInfoResp getById(Long id);

    Page<AdvistoryInfoResp> findByQuery(AdvistoryInfoQuery query);

    List<AdvistoryInfoResp> listByQuery(AdvistoryInfoQuery query);

    Long countByQuery(AdvistoryInfoQuery query);

    AdvistoryInfoResp createAndGet(AdvistoryInfoReq req, IOperatorAware operator);

    AdvistoryInfoResp update(AdvistoryInfoReq req, IOperatorAware operator);

    boolean remove(Long id, IOperatorAware operator);


}

