package top.atstudy.component.base.util.crypt;

import top.atstudy.component.base.config.SelfConfig;

public class TokenKeys {

    private static String[] tokenKeys = null;

    public TokenKeys(SelfConfig selfConfig) {
        tokenKeys = selfConfig.getTokenKeys().split(",");
    }

    private static final String[] keys = {"86af68d63bb3c9804de1824afd605", "20542037ab4FASDsNSnyyLwt51n"};
    public static String[] get() {
        return tokenKeys == null ? keys : tokenKeys;
    }
}
