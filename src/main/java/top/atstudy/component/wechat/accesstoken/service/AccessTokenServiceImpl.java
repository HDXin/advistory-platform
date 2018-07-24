package top.atstudy.component.wechat.accesstoken.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.atstudy.component.wechat.remote.accesstoken.resp.AccessTokenVo;
import top.atstudy.component.wechat.remote.accesstoken.service.IWechatAccessTokenService;

import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-06-26
 * Time: 18:42
 */
@Service
public class AccessTokenServiceImpl implements IAccessTokenService {
    private static final Logger logger = LoggerFactory.getLogger(AccessTokenServiceImpl.class);

    @Autowired
    private IWechatAccessTokenService wechatAccessTokenService;

    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 缓存 key
     */
    private static final String WECHAT_MINI_ACCESS_TOKEN_KEY = "WECHAT_MINI_ACCESS_TOKEN_KEY";
    /**
     * 默认只缓存 100 分组
     */
    private static final Integer DEFAULT_BOOK_ACCESS_TOKEN_KEY_VALID = 100;

    @Override
    public String getBookAccessToken() throws IOException {

        //1.获取 accessToken
        String accessToken = redisTemplate.opsForValue().get(WECHAT_MINI_ACCESS_TOKEN_KEY);
        Long seconds = redisTemplate.getExpire(WECHAT_MINI_ACCESS_TOKEN_KEY);

        if(seconds == null
                || seconds < 1
                || StringUtils.isEmpty(accessToken)){
            AccessTokenVo accessTokenVo = wechatAccessTokenService.getAccessToken();
            accessToken = accessTokenVo.getAccess_token();

            //放入缓存, 缓存 100 分钟
            redisTemplate.opsForValue().append(WECHAT_MINI_ACCESS_TOKEN_KEY, accessToken);
            redisTemplate.expireAt(WECHAT_MINI_ACCESS_TOKEN_KEY, DateUtils.addMinutes(new Date(), DEFAULT_BOOK_ACCESS_TOKEN_KEY_VALID));
        }

        logger.info(" ===>> get accesstoken response: {}", accessToken);
        return accessToken;
    }


}
