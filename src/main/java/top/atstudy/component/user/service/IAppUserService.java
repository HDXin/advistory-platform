package top.atstudy.component.user.service;


import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.user.SessionUser;
import top.atstudy.component.user.vo.req.AppUserQuery;
import top.atstudy.component.user.vo.req.AppUserReq;
import top.atstudy.component.user.vo.resp.AppUserResp;

import java.util.List;

/**
 * IAppUserService 接口
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IAppUserService {

    AppUserResp getById(Long id);

    Page<AppUserResp> findByQuery(AppUserQuery query);

    List<AppUserResp> listByQuery(AppUserQuery query);

    Long countByQuery(AppUserQuery query);

    AppUserResp createAndGet(AppUserReq req, IOperatorAware operator);

    AppUserResp update(AppUserReq req, IOperatorAware operator);

    boolean remove(Long id, IOperatorAware operator);

    /**
     * 获取 openid 对应的用户
     * @param openid
     * @return
     */
    AppUserResp getByOpenid(String openid, IOperatorAware operator);

    /**
     * 启用
     * @param userId
     * @param operator
     * @return
     */
    boolean enable(Long userId, IOperatorAware operator);

    /**
     * 禁用
     * @param userId
     * @param operator
     * @return
     */
    boolean disable(Long userId, IOperatorAware operator);
}

