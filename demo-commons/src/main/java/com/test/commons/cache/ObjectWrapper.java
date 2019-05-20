package com.test.commons.cache;

/**
 * 包装业务方法的返回值，防止防止缓存穿透的问题
 * 
 * @author wanghui59@jd.com
 * @since 2018-1-5
 * @param <T>
 */
public class ObjectWrapper<T> {

    private T value;
    
    public ObjectWrapper() {
    }
    
    public ObjectWrapper(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    
    public static <T> ObjectWrapper<T> wrap(T value) {
        return new ObjectWrapper<>(value);
    }
    
    public static <T> T unwrap(ObjectWrapper<T> objectWrapper) {
        return objectWrapper.value;
    }
    
}
