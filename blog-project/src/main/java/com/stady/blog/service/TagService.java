package com.stady.blog.service;

import com.stady.blog.pojo.Blog;
import com.stady.blog.pojo.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * tags service interface
 */
public interface TagService {

    /**
     * 分页
     * @param pageable
     * @return
     */
    Page<Tag> listTags(Pageable pageable);

    List<Tag> listTags();

    List<Tag> listTags(String ids);

    /**
     * 根据id 获取
     * @param id
     * @return
     */
    Tag getTag(Long id);

    /**
     * 保存
     * @param tag
     * @return
     */
    Tag saveTag(Tag tag);

    /**
     * 修改
     * @param id
     * @param tag
     * @return
     */
    Tag updateTag(Long id, Tag tag);

    /**
     * 删除
     * @param id
     */
    void deleteTag(Long id);

    /**
     * 根据name获取
     * @param name
     * @return
     */
    Tag getByName(String name);

    List<Tag> listTagTop(Integer size);


}
