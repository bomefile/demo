package com.test.commons.cache;

import java.util.concurrent.TimeUnit;

/**
 * 缓存抽象
 * 
 * @author wanghui59@jd.com
 * @since 2018-1-4
 */
public interface Cache {
    /**
     * put值
     * @param key
     * @param value
     * @param <T>
     */
    <T> void put(String key, ObjectWrapper<T> value);

    /**
     * put值带超时时间
     * @param key
     * @param value
     * @param expire
     * @param timeUnit 单位
     * @param <T>
     */
    <T> void put(String key, ObjectWrapper<T> value, long expire, TimeUnit timeUnit);

    /**
     * 取值
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> ObjectWrapper<T> get(String key, Class<T> clazz);
    
}
