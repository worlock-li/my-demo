package com.stady.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用 @ControllerAdvice 注解
 * @author 异常处理类
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * @ExceptionHandler注解标注这是一个异常处理类
     * */
    @ExceptionHandler
    public String exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        // : {} 表示占位符， 后面的参数会进行填充
        logger.error("request URL : {}, Exception : {}", request.getRequestURI(), e);

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", request.getRequestURI());
        map.put("errorMessage", e.getMessage());
        map.put("errorStackTrace", e.getStackTrace());
        request.setAttribute("ext", map);
        // 返回error.html
        return "error/error";
    }
}
