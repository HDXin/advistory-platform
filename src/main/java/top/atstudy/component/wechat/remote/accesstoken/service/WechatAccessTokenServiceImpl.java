package top.atstudy.component.wechat.remote.accesstoken.service;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.atstudy.component.base.util.HttpUtil;
import top.atstudy.component.wechat.remote.accesstoken.resp.AccessTokenVo;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-01
 * Time: 15:06
 */
@Service
public class WechatAccessTokenServiceImpl implements IWechatAccessTokenService {
    private static final Logger logger = LoggerFactory.getLogger(WechatAccessTokenServiceImpl.class);

    @Value("${wechat.mini.appid}")
    private String appid;

    @Value("${wechat.mini.appsecret}")
    private String appsecret;

    private static String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取有效的 access_token
     * @return
     */
    @Override
    public AccessTokenVo getAccessToken() throws IOException{

        //获取对应的 SECRET
        requestUrl = requestUrl.replace("APPID", appid).replace("APPSECRET", appsecret);

        JSONObject response = HttpUtil.get(requestUrl, null);
        logger.info(" ===>> get accesstoken response: {}", response.toJSONString());
        return JSONObject.toJavaObject(response, AccessTokenVo.class);
    }

}
