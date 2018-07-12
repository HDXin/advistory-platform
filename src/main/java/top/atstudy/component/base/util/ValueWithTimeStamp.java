package top.atstudy.component.base.util;

/**
 * @author Spector
 */
public class ValueWithTimeStamp<V> {
    private long expireTime;
    private V value;

    public ValueWithTimeStamp(long expireTime, V value) {
        this.expireTime = expireTime;
        this.value = value;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
