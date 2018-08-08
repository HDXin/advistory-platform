package top.atstudy.advistory.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-08-07
 * Time: 10:27
 */
@Component
public class LocalPayConfig {

    @Value("${pay.config.appid}")
    private String appid;
    @Value("${pay.config.mchid}")
    private String mch_id;
    @Value("${pay.config.notifyUrl}")
    private String notify_url;
    @Value("${pay.config.tradeType}")
    private String trade_type;
    @Value("${pay.config.key}")
    private String key;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
