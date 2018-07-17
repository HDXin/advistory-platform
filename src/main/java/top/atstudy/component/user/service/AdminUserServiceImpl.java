package top.atstudy.component.user.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.atstudy.advistory.base.enums.BadRequest;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.base.config.Constants;
import top.atstudy.component.base.util.crypt.PasswordCrypt;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumUserStatus;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.user.dao.IAdminUserDao;
import top.atstudy.component.user.dao.dto.AdminUserDTO;
import top.atstudy.component.user.dao.dto.AdminUserDTOExample;
import top.atstudy.component.user.vo.req.AdminUserQuery;
import top.atstudy.component.user.vo.req.AdminUserReq;
import top.atstudy.component.user.vo.req.PassVo;
import top.atstudy.component.user.vo.resp.AdminUserResp;

import java.util.List;

/**
 * IAdminUserService 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class AdminUserServiceImpl implements IAdminUserService {
    /******* Fields Area *******/

    private IAdminUserDao adminUserDao;

    /******* Construction Area *******/
    public AdminUserServiceImpl(@Autowired IAdminUserDao adminUserDao) {
        this.adminUserDao = adminUserDao;
    }

    @Override
    public AdminUserResp getById(Long id) {
        AdminUserResp target = null;
        AdminUserDTOExample example = new AdminUserDTOExample();
        AdminUserDTOExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(id);
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        List<AdminUserDTO> targets = this.adminUserDao.listByExample(example);
        AdminUserDTO targetDto = this.adminUserDao.getByExample(example);
        if (targetDto != null) {
            target = AdminUserResp.parseSinglet(targetDto);
        }
        return target;
    }

    @Override
    public Page<AdminUserResp> findByQuery(AdminUserQuery query) {
        Page<AdminUserDTO> targetPage = this.adminUserDao.findByQuery(query);
        Page<AdminUserResp> page = new Page<>(targetPage);
        page.setItems(AdminUserResp.parseList(targetPage.getItems()));
        return page;
    }

    @Override
    public List<AdminUserResp> listByQuery(AdminUserQuery query) {
        List<AdminUserDTO> targets = this.adminUserDao.listByQuery(query);
        return AdminUserResp.parseList(targets);
    }

    @Override
    public Long countByQuery(AdminUserQuery query) {
        return this.adminUserDao.countByQuery(query);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdminUserResp createAndGet(AdminUserReq req, IOperatorAware operator) throws APIException {

        //1.用户名不能为空
        if(StringUtils.isBlank(req.getUserName()))
            throw new APIException(BadRequest.ADMIN_USER_NAME_NULL);

        //2.判断用户是否已存在
        if(!validUserName(null, req.getUserName()))
            throw new APIException(BadRequest.ADMIN_USER_NAME_EXISTS);

        //3.创建用户
        AdminUserDTO target = req.convertToDTO();
        target.setDeleted(EnumDeleted.NORMAL);
        target.setOperator(operator, true);

        if(req.getUserStatus() == null)
            target.setUserStatus(EnumUserStatus.ENABLE);

        //初始化默认密码
        String pass = req.getPassword();
        if(StringUtils.isBlank(req.getPassword())){
            pass = Constants.DEFAULT_PASSWORD;
        }
        target.setPassword(PasswordCrypt.encrypt(pass));

        target = this.adminUserDao.createAndGet(target);
        return AdminUserResp.parseSinglet(target);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdminUserResp update(AdminUserReq req, IOperatorAware operator) throws APIException {

        //1.用户名不能为空
        if(StringUtils.isBlank(req.getUserName()))
            throw new APIException(BadRequest.ADMIN_USER_NAME_NULL);

        //2.判断用户是否已存在
        if(!validUserName(req.getUserId(), req.getUserName()))
            throw new APIException(BadRequest.ADMIN_USER_NAME_EXISTS);

        //3.修改用户信息
        AdminUserDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        //更新不修改密码
        target.setPassword(null);
        target = this.adminUserDao.updateAndGet(target);
        return AdminUserResp.parseSinglet(target);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        AdminUserDTO target = this.adminUserDao.getById(id);
        target.setOperator(operator, false);
        return this.adminUserDao.remove(target);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean enable(Long userId, IOperatorAware operator) {
        AdminUserDTO target = new AdminUserDTO();
        target.setOperator(operator, false);
        target.setUserId(userId);
        target.setUserStatus(EnumUserStatus.ENABLE);
        return this.adminUserDao.update(target);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean disable(Long userId, IOperatorAware operator) {
        AdminUserDTO target = new AdminUserDTO();
        target.setOperator(operator, false);
        target.setUserId(userId);
        target.setUserStatus(EnumUserStatus.DISABLE);
        return this.adminUserDao.update(target);
    }

    @Override
    public Boolean validUserName(Long userId, String userName) {

        AdminUserDTOExample example = new AdminUserDTOExample();
        AdminUserDTOExample.Criteria criteria = example.createCriteria();
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL)
                .andUserNameEqualTo(userName);

        //排除指定的用户
        if(userId != null)
            criteria.andUserIdNotEqualTo(userId);

        long count = this.adminUserDao.countByExample(example);
        return count > 0 ? false:true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean passReset(Long userId, IOperatorAware operator) {
        AdminUserDTO target = new AdminUserDTO();
        target.setOperator(operator, false);
        target.setUserId(userId);
        target.setPassword(PasswordCrypt.encrypt(Constants.DEFAULT_PASSWORD));
        return this.adminUserDao.update(target);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean passUpdate(PassVo passVo, IOperatorAware operator) throws APIException {

        //1.获取指定用户
        AdminUserDTO userInfo = this.adminUserDao.getById(passVo.getUserId());
        if(userInfo == null || userInfo.getUserId() == null)
            throw new APIException(BadRequest.ADMIN_USER_ID_INVALID);

        //2.校验密码
        String oldPass = PasswordCrypt.encrypt(passVo.getOldPass());
        if(!oldPass.equals(userInfo.getPassword()))
            throw new APIException(BadRequest.ADMIN_USER_OLD_PASS_INVALID);

        //3.修改密码
        AdminUserDTO target = new AdminUserDTO();
        target.setUserId(passVo.getUserId());
        target.setPassword(PasswordCrypt.encrypt(passVo.getNewPass()));
        target.setOperator(operator, false);

        return this.adminUserDao.update(target);
    }

    /******* GetSet Area ******/

    /******* Method Area *******/


}
