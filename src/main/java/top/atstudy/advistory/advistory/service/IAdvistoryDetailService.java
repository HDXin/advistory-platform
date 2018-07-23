package top.atstudy.advistory.advistory.service;


import top.atstudy.advistory.advistory.vo.req.AdvistoryDetailQuery;
import top.atstudy.advistory.advistory.vo.req.AdvistoryDetailReq;
import top.atstudy.advistory.advistory.vo.resp.AdvistoryDetailResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;

import java.util.List;

/**
 * IAdvistoryDetailService 接口
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IAdvistoryDetailService {

    AdvistoryDetailResp getById(Long id);

    Page<AdvistoryDetailResp> findByQuery(AdvistoryDetailQuery query);

    List<AdvistoryDetailResp> listByQuery(AdvistoryDetailQuery query);

    Long countByQuery(AdvistoryDetailQuery query);

    AdvistoryDetailResp createAndGet(AdvistoryDetailReq req, IOperatorAware operator);

    AdvistoryDetailResp update(AdvistoryDetailReq req, IOperatorAware operator);

    boolean remove(Long id, IOperatorAware operator);


}

