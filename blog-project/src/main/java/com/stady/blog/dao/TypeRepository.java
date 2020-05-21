package com.stady.blog.dao;
import com.stady.blog.pojo.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * type dao
 */
public interface TypeRepository extends JpaRepository<Type, Long> {
    /**
     * 查询type表中最大的id
     * @return
     */
    @Query(value = "SELECT MAX(id) FROM t_type")
    Long getMaxId();

    Type findByName(String name);

    @Query("select type from t_type type")
    List<Type> findTop(Pageable pageable);
}
