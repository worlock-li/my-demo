package com.springbootweb.springbootweb.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理登录的拦截器, 判断session中有没有登录的用户存在
 * 如果有放行， 如果没有，跳转到登陆页面
 * Interceptor： 拦截器
 * @author loginFilter
 */

public class LoginHandlerInterceptor implements HandlerInterceptor {

    // 在登陆之前进行处理的 handler
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            // 没有登陆
            request.setAttribute("msg", "未登录");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return false;
        } else {
            // 已经登录
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
