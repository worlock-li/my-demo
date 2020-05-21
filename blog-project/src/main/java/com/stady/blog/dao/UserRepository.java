package com.stady.blog.dao;

import com.stady.blog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 使用springboot jpa 来进行数据库的操作
 * @author UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 使用 jpa 操作数据库
     * 直接写方法， 不用写sql语句， 只要方法名遵循 jpa 的规则
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username, String password);

}
