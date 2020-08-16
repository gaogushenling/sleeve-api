package com.robinstudio.sleeveapi.service;

import com.robinstudio.sleeveapi.model.SpuDO;
import com.robinstudio.sleeveapi.repository.SpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuService {
    @Autowired
    public SpuRepository spuRepository;

    public SpuDO getSpu(Long id) {
        return this.spuRepository.findOneById(id);
    }

    public Page<SpuDO> getLatestPagingSpu(Integer pageNum, Integer size) {
        Pageable page = PageRequest.of(pageNum, size, Sort.by("createTime").descending());
        return this.spuRepository.findAll(page);
    }

    public Page<SpuDO> getByCategoryId(Long cid, Boolean isRoot, Integer pageNum, Integer size) {
        Pageable page = PageRequest.of(pageNum, size);
        if (isRoot) {
            return this.spuRepository.findByRootCategoryIdOrderByCreateTime(cid, page);
        } else {
            return this.spuRepository.findByCategoryIdOrderByCreateTime(cid, page);
        }
    }
}
