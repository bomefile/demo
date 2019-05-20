package com.test.commons.cache;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * CacheAside模式注解
 * 
 * @author wanghui59@jd.com
 * @since 2018-1-3
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheAside {

    String key();
    
    long expire() default 0L;
    
    TimeUnit timeUnit() default TimeUnit.SECONDS;

}
