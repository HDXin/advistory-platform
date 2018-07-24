package top.atstudy.component.wechat.accesstoken.service;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-06-26
 * Time: 18:41
 */
public interface IAccessTokenService {

    /**
     * 获取通讯的有效access_token
     * @return
     */
    String getBookAccessToken() throws IOException;


}
