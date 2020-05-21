package com.stady.blog.dao;

import com.stady.blog.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
    @Query("select b from t_blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from t_blog b where b.title like ?1 or b.description like ?1 ORDER BY ?#{#pageable}")
    Page<Blog> listBlogsQuery(Pageable pageable, String query);

    @Modifying
    @Query("update t_blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    @Query("select function('date_format', b.updateTime, '%Y') as year from t_blog b GROUP BY function('date_format', b.updateTime, '%Y') ORDER BY year desc")
    List<String> findGroupYears();

    @Query("select b from t_blog b where function('date_format', b.updateTime, '%Y') = ?1")
    List<Blog> findByYear(String year);


}
