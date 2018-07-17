package top.atstudy.component.user.service;


import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.user.SessionUser;
import top.atstudy.component.user.vo.req.AdminUserQuery;
import top.atstudy.component.user.vo.req.AdminUserReq;
import top.atstudy.component.user.vo.req.PassVo;
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

    AdminUserResp createAndGet(AdminUserReq req, IOperatorAware operator) throws APIException;

    AdminUserResp update(AdminUserReq req, IOperatorAware operator) throws APIException;

    boolean remove(Long id, IOperatorAware operator);

    /**
     * 启用
     * @param userId
     * @param operator
     * @return
     */
    Boolean enable(Long userId, IOperatorAware operator);

    /**
     * 禁用
     * @param userId
     * @param operator
     * @return
     */
    Boolean disable(Long userId, IOperatorAware operator);

    /**
     * 判断用户是否重复
     * @param userId
     * @param userName
     * @return
     */
    Boolean validUserName(Long userId, String userName);

    /**
     * 密码重置
     * @param userId
     * @param operator
     * @return
     */
    Boolean passReset(Long userId, IOperatorAware operator);

    /**
     * 修改用户密码
     * @param passVo
     * @param operator
     * @return
     */
    Boolean passUpdate(PassVo passVo, IOperatorAware operator) throws APIException;
}

