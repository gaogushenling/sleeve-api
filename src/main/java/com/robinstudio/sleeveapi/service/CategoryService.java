package com.robinstudio.sleeveapi.service;

import com.robinstudio.sleeveapi.model.CategoryDO;
import com.robinstudio.sleeveapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Map<Integer, List<CategoryDO>> getAll() {
        List<CategoryDO> roots = categoryRepository.findAllByIsRootOrderByIndexAsc(true);
        List<CategoryDO> subs = categoryRepository.findAllByIsRootOrderByIndexAsc(false);
        Map<Integer, List<CategoryDO>> categories = new HashMap<>();
        categories.put(1, roots);
        categories.put(2, subs);
        return categories;
    }
}
