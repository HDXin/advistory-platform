package top.atstudy.component.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.user.dao.IAdminUserDao;
import top.atstudy.component.user.dao.dto.AdminUserDTO;
import top.atstudy.component.user.dao.dto.AdminUserDTOExample;
import top.atstudy.component.user.vo.req.AdminUserQuery;
import top.atstudy.component.user.vo.req.AdminUserReq;
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
    public AdminUserResp createAndGet(AdminUserReq req, IOperatorAware operator) {
        AdminUserDTO target = req.convertToDTO();
        target.setOperator(operator, true);
        target = this.adminUserDao.createAndGet(target);
        return AdminUserResp.parseSinglet(target);
    }

    @Override
    public AdminUserResp update(AdminUserReq req, IOperatorAware operator) {
        AdminUserDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        target = this.adminUserDao.updateAndGet(target);
        return AdminUserResp.parseSinglet(target);
    }

    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        AdminUserDTO target = this.adminUserDao.getById(id);
        target.setOperator(operator, false);
        return this.adminUserDao.remove(target);
    }
    /******* GetSet Area ******/

    /******* Method Area *******/


}
