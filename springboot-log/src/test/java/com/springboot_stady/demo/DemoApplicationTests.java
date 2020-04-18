package com.springboot_stady.demo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    // 获取 logger 对象
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {
        logger.trace("这是trace日志记录");
        logger.debug("这是debug日志记录");
        // springboot默认使用的 info 级别， 可以在配置文件中进行配置
        logger.info("这是info日志记录");
        logger.warn("这是warn日志记录");
        logger.error("这是error日志记录");
    }

}
