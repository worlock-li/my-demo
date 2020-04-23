package com.springbootweb.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class TestController {

    /**
     * 加了 @ResponseBody 注解后，返回页面的是字符串， 不加跳转页面
     * @return
     */
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * 使用了 thymeleaf ，方法中带有的参数在前端页面上可以直接获取
     * @param map
     * @return
     */
    @RequestMapping("/success")
    public String success(HashMap<String, Object> map) {
        map.put("key", "value");
        return "success";
    }
}
