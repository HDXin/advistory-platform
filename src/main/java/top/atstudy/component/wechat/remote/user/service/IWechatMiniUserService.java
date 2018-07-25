package top.atstudy.component.wechat.remote.user.service;

import top.atstudy.component.auth.vo.AppAuthVo;
import top.atstudy.component.exception.APIException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-25
 * Time: 9:51
 */
public interface IWechatMiniUserService {

    /**
     * 根据 jscode 获取用户信息
     * @param jscode
     * @return
     */
    AppAuthVo getByJscode(String jscode) throws APIException;

}
