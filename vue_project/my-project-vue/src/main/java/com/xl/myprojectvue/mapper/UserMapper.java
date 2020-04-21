package com.xl.myprojectvue.mapper;

import com.xl.myprojectvue.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author UserMapper
 */
@Repository
@Mapper
public interface UserMapper {

    User getUserById(String id);

    List<User> findAll();
}
