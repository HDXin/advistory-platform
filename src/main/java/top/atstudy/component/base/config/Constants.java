package top.atstudy.component.base.config;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-12
 * Time: 20:09
 */
public class Constants {
    public static final String DEFAULT_PASSWORD = "123456";
    public static final String SESSION = "session";
    public static final String SESSION_AUTH_TOKEN = "session_authToken";
    public static final String SESSION_USER_ID = "userId";
    public static final String SESSION_USER_KEY = "sessionUser";
    public static final String SESSION_USER_TYPE = "userType";

    public static final int DEFAULT_PAGE_OFFSET = 0;
    public static final int DEFAULT_PAGE_LIMIT = 15;

    public static final int DEFAULT_LIMIT = 100;

    /**
     * AuthToken 活跃时间（即自动续期最大间隔）（24小时）
     */
    public static final int AUTH_TOKEN_AGE_ACTIVE = 24 * 3600;
    /**
     * AuthToken 最长过期时间（2周）
     */
//    public static final int AUTH_TOKEN_AGE_MAX = 14 * 24 * 3600;

    /**
     * WEB AuthToken 最长过期时间（2小时）
     */
    public static final int WEB_AUTH_TOKEN_AGE_MAX = 2 * 3600;
    /**
     * APP AuthToken 最长过期时间（1年）
     */
    public static final int AUTH_TOKEN_AGE_MAX = 365 * 24 * 3600;
    public static final String AUTH_TOKEN_NAME = "_MCH_AT";

    /**
     * token默认名称
     */
    public static final String AUTH_TOKEN_NAME_DEFAULT = "token";

    public static final String EXPLORER_SESSION = "explorer_session";
    public static final String EXPLORER_SESSION_USER_ID = "explorer_userId";
    public static final String EXPLORER_SESSION_USER_TYPE = "explorer_userType";

    /**
     * 默认编码格式
     */
    public static final String UTF8 = "UTF-8";

    public static final int ONE_HOUR_TIME = 60 * 60;
}
