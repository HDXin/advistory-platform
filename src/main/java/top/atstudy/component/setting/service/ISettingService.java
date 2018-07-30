package top.atstudy.component.setting.service;

import top.atstudy.component.base.IOperatorAware;
import top.atstudy.component.base.Page;
import top.atstudy.component.exception.APIException;
import top.atstudy.component.setting.vo.req.SettingQuery;
import top.atstudy.component.setting.vo.req.SettingReq;
import top.atstudy.component.setting.vo.resp.SettingResp;
import top.atstudy.component.user.AdminSessionUser;
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

    /**
     * 编辑配置文件
     * @param req
     * @param operator
     * @return
     */
    SettingResp updateByKey(SettingReq req, IOperatorAware operator) throws APIException;

    /**
     * 根据 key 获取配置
     * @param configKey
     * @return
     */
    SettingResp getByKey(String configKey);

}

