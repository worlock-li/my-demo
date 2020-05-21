package com.stady.blog.dao;

import com.stady.blog.pojo.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findTagByName(String name);

    @Query("select tag from t_tag tag")
    List<Tag> findTop(Pageable pageable);


}
