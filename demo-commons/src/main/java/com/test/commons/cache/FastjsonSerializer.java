package com.test.commons.cache;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jd.paipai.commons.utils.JsonUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 序列化器的Fastjson实现
 * 
 * @author wanghui59@jd.com
 * @since 2018-1-3
 */
public class FastjsonSerializer implements Serializer {
    
    private Charset charset = StandardCharsets.UTF_8;
    
    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    @Override
    public byte[] serialize(Object source) {
        if (source == null) {
            return new byte[0];
        }
        return JsonUtils.toJson(source).getBytes(charset);
    }

    @Override
    public byte[] serialize(Object source, SerializerFeature... features) {
        if (source == null) {
            return new byte[0];
        }
        return JsonUtils.toJson(source, features).getBytes(charset);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        if (bytes != null && bytes.length != 0) {
            return JsonUtils.fromJson(new String(bytes, charset), clazz); // 类型明确
        }
        return null;
    }

}
