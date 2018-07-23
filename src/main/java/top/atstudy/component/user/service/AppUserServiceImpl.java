package top.atstudy.component.user.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.enums.EnumUserStatus;
import top.atstudy.component.enums.EnumUserType;
import top.atstudy.component.user.dao.IAppUserDao;
import top.atstudy.component.user.dao.dto.AppUserDTO;
import top.atstudy.component.user.dao.dto.AppUserDTOExample;
import top.atstudy.component.user.vo.req.AppUserQuery;
import top.atstudy.component.user.vo.req.AppUserReq;
import top.atstudy.component.user.vo.resp.AppUserResp;
import java.util.List;

/**
 * IAppUserService 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class AppUserServiceImpl implements IAppUserService {
    /******* Fields Area *******/

    private IAppUserDao appUserDao;

    /******* Construction Area *******/
    public AppUserServiceImpl(@Autowired IAppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    @Override
    public AppUserResp getById(Long id) {
        AppUserResp target = null;
        AppUserDTOExample example = new AppUserDTOExample();
        AppUserDTOExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(id);
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        List<AppUserDTO> targets = this.appUserDao.listByExample(example);
        AppUserDTO targetDto = this.appUserDao.getByExample(example);
        if (targetDto != null) {
            target = AppUserResp.parseSinglet(targetDto);
        }
        return target;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AppUserResp getByOpenid(String openid, IOperatorAware operator) {

        AppUserDTO appUserDTO = this.appUserDao.getByOpenid(openid);
        if(appUserDTO != null && appUserDTO.getUserId() != null)
            return AppUserResp.parseSinglet(appUserDTO);

        AppUserDTO target = new AppUserDTO();
        target.setUserName(openid);
        target.setOpenid(openid);
        target.setUserStatus(EnumUserStatus.ENABLE);
        target.setUserType(EnumUserType.LEVEL_A);

        target.setOperator(operator, true);

        return AppUserResp.parseSinglet(this.appUserDao.createAndGet(target));
    }

    @Override
    public Page<AppUserResp> findByQuery(AppUserQuery query) {
        Page<AppUserDTO> targetPage = this.appUserDao.findByQuery(query);
        Page<AppUserResp> page = new Page<>(targetPage);
        page.setItems(AppUserResp.parseList(targetPage.getItems()));
        return page;
    }

    @Override
    public List<AppUserResp> listByQuery(AppUserQuery query) {
        List<AppUserDTO> targets = this.appUserDao.listByQuery(query);
        return AppUserResp.parseList(targets);
    }

    @Override
    public Long countByQuery(AppUserQuery query) {
        return this.appUserDao.countByQuery(query);
    }

    @Override
    public AppUserResp createAndGet(AppUserReq req, IOperatorAware operator) {
        AppUserDTO target = req.convertToDTO();
        target.setOperator(operator, true);
        target = this.appUserDao.createAndGet(target);
        return AppUserResp.parseSinglet(target);
    }

    @Override
    public AppUserResp update(AppUserReq req, IOperatorAware operator) {
        AppUserDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        target = this.appUserDao.updateAndGet(target);
        return AppUserResp.parseSinglet(target);
    }

    @Override
    public boolean enable(Long userId, IOperatorAware operator) {
        AppUserDTO target = new AppUserDTO();
        target.setUserId(userId);
        target.setUserStatus(EnumUserStatus.ENABLE);
        target.setOperator(operator, false);
        return this.appUserDao.update(target);
    }

    @Override
    public boolean disable(Long userId, IOperatorAware operator) {
        AppUserDTO target = new AppUserDTO();
        target.setUserId(userId);
        target.setUserStatus(EnumUserStatus.DISABLE);
        target.setOperator(operator, false);
        return this.appUserDao.update(target);
    }

    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        AppUserDTO target = this.appUserDao.getById(id);
        target.setOperator(operator, false);
        return this.appUserDao.remove(target);
    }
    /******* GetSet Area ******/

    /******* Method Area *******/


}
