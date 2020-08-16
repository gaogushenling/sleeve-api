package com.robinstudio.sleeveapi.repository;

import com.robinstudio.sleeveapi.model.SpuDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpuRepository extends JpaRepository<SpuDO, Long> {
    SpuDO findOneById(Long id);

    Page<SpuDO> findByCategoryIdOrderByCreateTime(Long cid, Pageable page);

    Page<SpuDO> findByRootCategoryIdOrderByCreateTime(Long cid, Pageable page);
}
