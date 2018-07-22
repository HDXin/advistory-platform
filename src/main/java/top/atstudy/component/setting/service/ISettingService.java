package top.atstudy.component.setting.service;

import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.setting.vo.req.SettingQuery;
import top.atstudy.component.setting.vo.req.SettingReq;
import top.atstudy.component.setting.vo.resp.SettingResp;
import java.util.List;

/**
 * ISettingService 接口
 *
 * =========================================
 * <p>
 * Contributors :
 * Mybatis auto generator
 */
public interface ISettingService {

    SettingResp getById(Long id);

    Page<SettingResp> findByQuery(SettingQuery query);

    List<SettingResp> listByQuery(SettingQuery query);

    Long countByQuery(SettingQuery query);

    SettingResp createAndGet(SettingReq req, IOperatorAware operator);

    SettingResp update(SettingReq req, IOperatorAware operator);

    boolean remove(Long id, IOperatorAware operator);


}

