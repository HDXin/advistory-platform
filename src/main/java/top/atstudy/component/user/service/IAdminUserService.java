package top.atstudy.component.user.service;


import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.user.vo.req.AdminUserQuery;
import top.atstudy.component.user.vo.req.AdminUserReq;
import top.atstudy.component.user.vo.resp.AdminUserResp;
import java.util.List;

/**
 * IAdminUserService 接口
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface IAdminUserService {

    AdminUserResp getById(Long id);

    Page<AdminUserResp> findByQuery(AdminUserQuery query);

    List<AdminUserResp> listByQuery(AdminUserQuery query);

    Long countByQuery(AdminUserQuery query);

    AdminUserResp createAndGet(AdminUserReq req, IOperatorAware operator);

    AdminUserResp update(AdminUserReq req, IOperatorAware operator);

    boolean remove(Long id, IOperatorAware operator);


}

