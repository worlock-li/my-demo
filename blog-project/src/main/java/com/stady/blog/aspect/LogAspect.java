package com.stady.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 使用切面编程思想进行日志管理
 * @Aspect 注解， 表明这个类是一个aop的类
 * @author LogAspect
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @Pointcut("execution(* com.stady.blog.web.*.*(..))")
     * 指定拦截需要处理的类
     */
    @Pointcut("execution(* com.stady.blog.web.admin.*.*(..))")
    public void log() {

    }

    /**
     * 在切点执行之前执行的类
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        // JoinPoint 为切面对象， 里面可以获取拦截的类的名字和方法的名字

        // 获取HttpServletRequest对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取当前请求的路径
        String url = request.getRequestURL().toString();
        // 获取当前请求的ip
        String ip = request.getRemoteAddr();
        // 从切面对象里面获取类名和方法名
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        // 获取切面对象获取请求参数
        Object[] args = joinPoint.getArgs();
        // 创建内部类对象， 传递参数
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        // 日志输出
        logger.info("request : {}" ,requestLog);
    }

    /**
     * 在切点之后执行的类
     */
    @After("log()")
    public void doAfter() {
        // logger.info("===========doAfter=============");
    }

    /**
     * 方法执行后返回的参数
     * @param result
     */
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        logger.info("doAfterReturnResult : {}" , result);
    }

    private static class RequestLog {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        public RequestLog() {
        }

        @Override
        public String toString() {
            return "{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
    
}
