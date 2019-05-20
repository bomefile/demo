package com.test.commons.cache;

import com.test.commons.spring.SpringElUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 缓存切面
 * 
 * @author wanghui59@jd.com
 * @since 2018-1-3
 */
@Aspect
public class CacheInterceptor {
    protected static Logger logger = LoggerFactory.getLogger(CacheInterceptor.class);
    /**
     * 缓存抽象
     */
    private Cache cache;
    
    /**
     * 参数名解析器
     */
    private ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
    
    public void setCache(Cache cache) {
        this.cache = cache;
    }
    
    public void setParameterNameDiscoverer(ParameterNameDiscoverer parameterNameDiscoverer) {
        this.parameterNameDiscoverer = parameterNameDiscoverer;
    }

    @Pointcut("@annotation(com.test.commons.cache.CacheAside)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        Signature signature = jp.getSignature();
        Method method = ((MethodSignature) signature).getMethod();
        // 方法上的注解
        CacheAside cacheAsideAnno = AnnotationUtils.findAnnotation(method, CacheAside.class);
        String key = generateKey(jp, method, cacheAsideAnno);
        logger.debug("缓存key={}", key);
        return cacheAside(jp, key, method.getReturnType(), cacheAsideAnno);
    }
    
    protected String generateKey(ProceedingJoinPoint jp, Method method, CacheAside cacheAsideAnno) {
        // 构造SpringExpression变量
        Map<String, Object> variables = getVariables(jp.getArgs(), method);
        // 计算SpEL的值
        return SpringElUtils.parseExpression(cacheAsideAnno.key(), variables, String.class);
    }
    
    private Map<String, Object> getVariables(Object[] args, Method method) {
        // key是参数名, value是参数值
        Map<String, Object> variables = new HashMap<>(args.length);
        if (args != null) {
            // 获取参数名
            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            for (int i = 0; i < args.length; i++) {
                String paramName = parameterNames[i];
                Object arg = args[i];
                variables.put(paramName, arg);
            }
        }
        return variables;
    }

    /**
     * 先从缓存中查，如果缓存中没有则从数据库中查询, 并放入缓存
     * 
     * @param jp
     * @param key
     * @param type
     * @param cacheAsideAnno
     * @return
     * @throws Throwable
     */
    @SuppressWarnings("unchecked")
    protected <T> T cacheAside(ProceedingJoinPoint jp, String key, Class<T> type, CacheAside cacheAsideAnno) throws Throwable {
        ObjectWrapper<T> cached = cache.get(key, type); // 从缓存中取
        if (cached != null) { // 缓存不为空
            return ObjectWrapper.unwrap(cached); // 返回缓存
        }
        Object returnValue = jp.proceed(); // 执行方法
        
        /**
         * 包装返回值，使写入缓存的数据不为null
         * 
         * 如写入缓存的数据为null，那么下一次CacheAside还会查询数据库，这样就造成了缓存穿透
         * 在流量较大时，对数据库性能影响较大
         */
        ObjectWrapper<T> wrappedReturnValue = ObjectWrapper.wrap((T) returnValue);
        if (cacheAsideAnno.expire() > 0L) { // 设置了过期时间
            cache.put(key, wrappedReturnValue, cacheAsideAnno.expire(), cacheAsideAnno.timeUnit());
        } else { // 没有设置过期时间
            cache.put(key, wrappedReturnValue);
        }
        return (T) returnValue;
    }

}
