package top.atstudy.component.base.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheMap<K, V> {
    private Map<K, ValueWithTimeStamp<V>> cache = new ConcurrentHashMap<K, ValueWithTimeStamp<V>>();

    public void put(K key, V value, int ttl){
        long expireTime = System.currentTimeMillis() + ttl * 1000;
        ValueWithTimeStamp<V> valueWithTimeStamp = new ValueWithTimeStamp<V>(expireTime, value);
        cache.put(key, valueWithTimeStamp);
    }

    public V get(K key){
        ValueWithTimeStamp<V> valueWithTimeStamp = cache.get(key);
        if(valueWithTimeStamp == null){
            return null;
        }

        long expireTime = valueWithTimeStamp.getExpireTime();
        long currentTime = System.currentTimeMillis();
        //key已经过期，删除，返回null
        if(currentTime > expireTime){
            System.out.println("key :" + key + " is expired.");
            cache.remove(key);
            return null;
        }

        return cache.get(key).getValue();
    }
}
