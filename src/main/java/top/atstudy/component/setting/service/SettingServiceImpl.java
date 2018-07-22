package top.atstudy.component.setting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.enums.EnumDeleted;
import top.atstudy.component.setting.dao.ISettingDao;
import top.atstudy.component.setting.dao.dto.SettingDTO;
import top.atstudy.component.setting.dao.dto.SettingDTOExample;
import top.atstudy.component.setting.vo.req.SettingQuery;
import top.atstudy.component.setting.vo.req.SettingReq;
import top.atstudy.component.setting.vo.resp.SettingResp;
import java.util.List;

/**
 * ISettingService 实现类
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
@Service
public class SettingServiceImpl implements ISettingService {
    /******* Fields Area *******/

    private ISettingDao settingDao;

    /******* Construction Area *******/
    public SettingServiceImpl(@Autowired ISettingDao settingDao) {
        this.settingDao = settingDao;
    }

    @Override
    public SettingResp getById(Long id) {
        SettingResp target = null;
        SettingDTOExample example = new SettingDTOExample();
        SettingDTOExample.Criteria criteria = example.createCriteria();
        criteria.andSettingIdEqualTo(id);
        criteria.andDeletedEqualTo(EnumDeleted.NORMAL);
        List<SettingDTO> targets = this.settingDao.listByExample(example);
        SettingDTO targetDto = this.settingDao.getByExample(example);
        if (targetDto != null) {
            target = SettingResp.parseSinglet(targetDto);
        }
        return target;
    }



    @Override
    public Page<SettingResp> findByQuery(SettingQuery query) {
        Page<SettingDTO> targetPage = this.settingDao.findByQuery(query);
        Page<SettingResp> page = new Page<>(targetPage);
        page.setItems(SettingResp.parseList(targetPage.getItems()));
        return page;
    }

    @Override
    public List<SettingResp> listByQuery(SettingQuery query) {
        List<SettingDTO> targets = this.settingDao.listByQuery(query);
        return SettingResp.parseList(targets);
    }

    @Override
    public Long countByQuery(SettingQuery query) {
        return this.settingDao.countByQuery(query);
    }

    @Override
    public SettingResp createAndGet(SettingReq req, IOperatorAware operator) {
        SettingDTO target = req.convertToDTO();
        target.setOperator(operator, true);
        target = this.settingDao.createAndGet(target);
        return SettingResp.parseSinglet(target);
    }

    @Override
    public SettingResp update(SettingReq req, IOperatorAware operator) {
        SettingDTO target = req.convertToDTO();
        target.setOperator(operator, false);
        target = this.settingDao.updateAndGet(target);
        return SettingResp.parseSinglet(target);
    }

    @Override
    public boolean remove(Long id, IOperatorAware operator) {
        SettingDTO target = this.settingDao.getById(id);
        target.setOperator(operator, false);
        return this.settingDao.remove(target);
    }
    /******* GetSet Area ******/

    /******* Method Area *******/


}
