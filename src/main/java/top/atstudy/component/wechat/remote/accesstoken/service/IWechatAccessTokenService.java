package top.atstudy.component.wechat.remote.accesstoken.service;

import top.atstudy.component.wechat.remote.accesstoken.resp.AccessTokenVo;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-01
 * Time: 15:05
 */
public interface IWechatAccessTokenService {

    /**
     * 获取指定应用的 access_token
     * @return
     */
    AccessTokenVo getAccessToken() throws IOException;

}
