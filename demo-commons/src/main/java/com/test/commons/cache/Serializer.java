package com.test.commons.cache;

import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 序列化器，主要用于对缓存value的序列化和反序列
 * 
 * @author wanghui59@jd.com
 * @since 2018-1-3
 */
public interface Serializer {

    byte[] serialize(Object object);

    byte[] serialize(Object source, SerializerFeature... features);

    <T> T deserialize(byte[] bytes, Class<T> clazz);
    
}
