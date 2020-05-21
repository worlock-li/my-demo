package com.stady.blog.service;

import com.stady.blog.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    /**
     * 保存
     * @param type
     * @return
     */
    Type saveType(Type type);

    /**
     * 获取type
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 分页
     * @param pageable
     * @return
     */
    Page<Type> listType(Pageable pageable);

    /**
     * 修改
     * @param id
     * @param type
     * @return
     */
    Type updateType(Long id, Type type);

    /**
     * 删除
     * @param id
     */
    void deleteType(Long id);

    List<Type> listType();

    /**
     * 查询type表中最大的id
     * @return
     */
    long getMaxId();

    Type getTypeByName(String name);

    List<Type> listTypeTop(Integer size);
}
