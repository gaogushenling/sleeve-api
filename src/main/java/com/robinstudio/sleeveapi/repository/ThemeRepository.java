package com.robinstudio.sleeveapi.repository;

import com.robinstudio.sleeveapi.model.ThemeDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository extends JpaRepository<ThemeDO, Long> {
    /**
     * JPQL Java Persistence Query Language Jpa 提供的操作模型的查询语句
     * (:[param_name]) 是固定写法 引用的是@Param的参数名
     * 此时 已经不是Jpa的命名方法，只是一个普通的方法
     * 命名方法中也有SQL的in查询
     * @Query("")  -> OPTION + ENTER -> Edit Spring Data QL Fragment -> 编写SQL
     */
    @Query("select t from ThemeDO t where t.name in (:names)")
    List<ThemeDO> findByNames(@Param("names") List<String> names);

    Optional<ThemeDO> findByName(String name);
}
