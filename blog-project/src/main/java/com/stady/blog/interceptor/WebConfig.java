package com.stady.blog.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 配置类，
 * 配置过滤器的作用范围
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 拦截器作用范围是 admin 下面的所有路径
     * 忽略 /admin， /admin/login
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // 为了防止登录成功后，刷新页面，表单重复提交的问题
                // 使用重定向的方式来进行页面的跳转
                // 这里的 main.html 就是最后跳转的路径， 它对应的真实路径为 admin/index
                // 这样在登录成功后， 浏览器上面的路径就是 main.html
                // 刷新页面不会提交表单
                registry.addViewController("admin/main.html").setViewName("admin/index");
            }
        };
        return adapter;
    }
}
