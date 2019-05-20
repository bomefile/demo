package com.test.commons.spring;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;

/**
 * 基于Spring EL的工具类
 * 
 * @author wanghui59@jd.com
 * @since 2018-1-3
 */
public class SpringElUtils {

    /**
     * 计算SpringEL表达式的值
     * @param expressionString Spring EL表达式
     * @param variables 参数
     * @param resultType 返回值类型
     * @return
     */
    public static <T> T parseExpression(String expressionString, Map<String, Object> variables, Class<T> resultType) {
        EvaluationContext context = new StandardEvaluationContext();
        variables.forEach((name, value) -> context.setVariable(name, value));
        ExpressionParser parser = new SpelExpressionParser();
        return parser.parseExpression(expressionString).getValue(context, resultType);
    }
    
}
