package com.robinstudio.sleeveapi.repository;

import com.robinstudio.sleeveapi.model.CategoryDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryDO, Long> {
    List<CategoryDO> findAllByIsRootOrderByIndexAsc(Boolean isRoot);
}
