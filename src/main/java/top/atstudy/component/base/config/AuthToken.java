package top.atstudy.component.base.config;

import top.atstudy.component.base.exception.EncryptException;
import top.atstudy.component.base.util.TokenEncryption;
import top.atstudy.component.base.util.crypt.TokenKeys;

/**
 * Created by Spector on 2017/6/27.
 */
public class AuthToken {

    private static final String SEPARATOR = ",";

    private static final TokenEncryption TOKEN_ENCRYPTION = new TokenEncryption(TokenKeys.get());

    public final long userId;
    public final String userName ;
    public final long active;
    public final long expiry;
    public final String rand;

    /**
     *
     * @param userId
     * @param userName
     * @param active
     *            when the token is active (in milliseconds)
     * @param expiry
     *            when the token is expired (in milliseconds)
     */
    public AuthToken(long userId, String userName, long active, long expiry, String rand) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.active = active;
        this.expiry = expiry;
        this.rand = rand;
    }

    /**
     * Create the token string.
     *
     * @return encrypted string
     * @throws EncryptException
     */
    public String token() throws EncryptException {
        String unencrypted = new StringBuilder()
                .append(this.userId).append(SEPARATOR)
                .append(this.userName).append(SEPARATOR)
                .append(this.active).append(SEPARATOR)
                .append(this.expiry).append(SEPARATOR)
                .append(this.rand).toString();
        String token = TOKEN_ENCRYPTION.encrypt(unencrypted);
        return token;
    }

    public static boolean isActive(AuthToken authToken) {
        long now = System.currentTimeMillis();
        return authToken != null && 0 != authToken.userId && now > authToken.active && now < authToken.expiry;
    }

    public static boolean isActive(String token) throws EncryptException {
        return isActive(parse(token));
    }

    public static AuthToken parse(String token) throws EncryptException {
        String decrypt = TOKEN_ENCRYPTION.decrypt(token);
        String[] arr = decrypt.split(SEPARATOR);
        if (arr != null && arr.length == 5) {
            int idx = 0;
            long userId = Long.valueOf(arr[idx++]);
            String userName = arr[idx++];
            long active = Long.valueOf(arr[idx++]);
            long expiry = Long.valueOf(arr[idx++]);
            String rand = arr[idx++];

            return new AuthToken(userId, userName, active, expiry, rand);
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AuthToken [userId=").append(userId).append(", active=").append(active)
                .append(", userName=").append(userName).append(", expiry=").append(expiry).append(", rand=").append
                (rand).append("]");
        return builder.toString();
    }

}
