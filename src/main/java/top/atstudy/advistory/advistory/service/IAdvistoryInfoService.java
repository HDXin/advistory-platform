package top.atstudy.advistory.advistory.service;


import top.atstudy.advistory.advistory.vo.req.AdvistoryInfoQuery;
import top.atstudy.advistory.advistory.vo.req.AdvistoryInfoReq;
import top.atstudy.advistory.advistory.vo.resp.AdvistoryInfoResp;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.exception.APIException;

import java.text.ParseException;
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

    AdvistoryInfoResp createAndGet(AdvistoryInfoReq req, IOperatorAware operator) throws ParseException;

    AdvistoryInfoResp update(AdvistoryInfoReq req, IOperatorAware operator) throws ParseException;

    boolean remove(Long id, IOperatorAware operator);

    boolean addReadNumber(Long advistoryId);

    boolean addFavoriteNumber(Long advistoryId);

    boolean subFavoriteNumber(Long advistoryId);
}

