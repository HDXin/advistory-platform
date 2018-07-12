package top.atstudy.component.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Spector on 2017/6/28.
 */
@Component
public class SelfConfig {
    @Value("${component.auth.token.switch}")
    private String authTokenSwitch;

    @Value("${component.parameter.check.switch}")
    private String parameterCheckSwitch;

    @Value("${component.user.exclude.urlPatterns}")
    private String urlPatterns;

    @Value("${cookie.domain}")
    private String cookieDomain;

    @Value("${token.keys}")
    private String tokenKeys;

    public String getAuthTokenSwitch() {
        return authTokenSwitch;
    }

    public SelfConfig setAuthTokenSwitch(String authTokenSwitch) {
        this.authTokenSwitch = authTokenSwitch;
        return this;
    }

    public String getParameterCheckSwitch() {
        return parameterCheckSwitch;
    }

    public SelfConfig setParameterCheckSwitch(String parameterCheckSwitch) {
        this.parameterCheckSwitch = parameterCheckSwitch;
        return this;
    }

    public String getUrlPatterns() {
        return urlPatterns;
    }

    public SelfConfig setUrlPatterns(String urlPatterns) {
        this.urlPatterns = urlPatterns;
        return this;
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public SelfConfig setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
        return this;
    }

    public String getTokenKeys() {
        return tokenKeys;
    }

    public SelfConfig setTokenKeys(String tokenKeys) {
        this.tokenKeys = tokenKeys;
        return this;
    }
}
