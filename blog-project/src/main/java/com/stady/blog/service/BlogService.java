package com.stady.blog.service;

import com.stady.blog.pojo.Blog;
import com.stady.blog.pojo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<Blog> listBlogs(Pageable pageable, BlogQuery blog);

    /**
     * 分页查询不需要查询条件
     * @param pageable
     * @return
     */
    Page<Blog> listBlogs(Pageable pageable);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    Blog getById(Long id);

    /**
     * 获取单个blog并转换markdown文本
     * @return
     */
    Blog getOneAndConvert(Long id);

    /**
     * 新增
     * @param blog
     * @return
     */
    Blog save(Blog blog);

    /**
     * 修改
     * @param id
     * @param blog
     * @return
     */
    Blog update(Long id, Blog blog);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 查询推荐博客列表
     * @param size
     * @return
     */
    List<Blog> listRecommendBlogTop(Integer size);

    Page<Blog> listBlogsQuery(String query, Pageable pageable);

    Page<Blog> listBlog(Long tagId, Pageable pageable);

    Map<String, List<Blog>> archvieBlog();

    Long countBlog();
}
