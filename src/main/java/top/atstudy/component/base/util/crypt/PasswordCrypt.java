package top.atstudy.component.base.util.crypt;


import org.apache.commons.codec.digest.Crypt;
import org.apache.commons.codec.digest.DigestUtils;

public abstract class PasswordCrypt {
    private static final String CRYPT_SALT = "kdxwarranty";

    public static String encrypt(String pwd) {
        String crypt = Crypt.crypt(pwd, CRYPT_SALT);
        return DigestUtils.md5Hex(crypt);
    }
}
