package com.stady.blog.service.impl;

import com.stady.blog.dao.BlogRepository;
import com.stady.blog.exception.NotFoundException;
import com.stady.blog.pojo.Blog;
import com.stady.blog.pojo.BlogQuery;
import com.stady.blog.pojo.Type;
import com.stady.blog.service.BlogService;
import com.stady.blog.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Page<Blog> listBlogs(Pageable pageable, BlogQuery blog) {
        // 进行查询分页
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                // 条件一 ： 根据博客标题查询
                // 当输入了标题时， 添加该条件查询到predicates里面
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {
                    predicates.add(cb.like(root.<String>get("title"), blog.getTitle()));
                }

                // 条件二 ： 根据博客类型查询
                // 当选择了类型时， 添加该条件查询到predicates里面
                if (blog.getTypeId() != null) {
                    predicates.add(cb.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }

                // 条件三 ： 根据是否推荐查询
                // 选中推荐时， 返回值为true， 添加该条件查询到predicates里面
                if (blog.isRecommend()) {
                    predicates.add(cb.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
                // 执行语句， where相当于 sql 中的where，进行条件的查询
                // 里面的参数是一个 条件数组， 将predicates转化为一个数组， 指定长度为 predicates 的 size
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Page<Blog> listBlogs(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public Blog getById(Long id) {
        return blogRepository.getOne(id);
    }

    @Transactional
    @Override
    public Blog getOneAndConvert(Long id) {
        Blog blog = blogRepository.getOne(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        blogRepository.updateViews(id);
        return b;
    }

    @Transactional
    @Override
    public Blog save(Blog blog) {
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            Blog blog1 = blogRepository.getOne(blog.getId());
            blog.setCreateTime(blog1.getCreateTime());
            blog.setViews(blog1.getViews());
            blog.setUpdateTime(new Date());
        }

        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog update(Long id, Blog blog) {
        Blog bg = blogRepository.getOne(id);
        if (bg == null) {
            throw new NotFoundException("不存在该博客");
        }
        BeanUtils.copyProperties(blog, bg);
        return blogRepository.save(bg);
    }

    @Override
    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogRepository.findTop(pageable);
    }

    @Override
    public Page<Blog> listBlogsQuery(String query, Pageable pageable) {
        return blogRepository.listBlogsQuery(pageable, query);
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join<Object, Object> join = root.join("tags");
                return cb.equal(join.get("id"), tagId);
            }
        }, pageable);
    }

    @Override
    public Map<String, List<Blog>> archvieBlog() {
        List<String> years = blogRepository.findGroupYears();
        Map<String, List<Blog>> map = new LinkedHashMap<>();
        for (String year : years) {
            map.put(year, blogRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogRepository.count();
    }


}
