package top.atstudy.component.wechat.remote.user.service;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.atstudy.advistory.base.enums.http.BadRequest;
import top.atstudy.advistory.base.enums.http.Forbidden;
import top.atstudy.component.auth.vo.AppAuthVo;
import top.atstudy.component.base.util.HttpUtil;
import top.atstudy.component.exception.APIException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-25
 * Time: 9:52
 */
@Service
public class WechatMiniUserServiceImpl implements IWechatMiniUserService {
    private static final Logger logger = LoggerFactory.getLogger(WechatMiniUserServiceImpl.class);

    @Value("${wechat.mini.appid}")
    private String appid;

    @Value("${wechat.mini.appsecret}")
    private String appsecret;

    //微信小程序
    //获取有效 openid
    public final static String WECHAT_MINI_PROGRAM_ACCESS_TOCKEN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=APPSECRET&js_code=JSCODE&grant_type=authorization_code";

    @Override
    public AppAuthVo getByJscode(String jscode) throws APIException {

        AppAuthVo appAuthVo = new AppAuthVo();
        String requestUrl = WECHAT_MINI_PROGRAM_ACCESS_TOCKEN_URL
                .replace("APPID", appid)
                .replace("APPSECRET", appsecret)
                .replace("JSCODE", jscode);
        JSONObject jsonObject = HttpUtil.get(requestUrl, null);
        // 如果请求成功
        if (null != jsonObject && null == jsonObject.getInteger("errcode")) {
            appAuthVo.setOpenid(jsonObject.getString("openid"));
            appAuthVo.setSessionKey(jsonObject.getString("session_key"));
        } else {
            logger.error("获取小程序 openid 异常"+ jsonObject.toJSONString());
            throw new APIException(Forbidden.WECHAT_USER_INFO_ERROR);
        }

        return appAuthVo;
    }
}
