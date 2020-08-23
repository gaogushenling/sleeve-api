package com.robinstudio.sleeveapi.service;

import com.robinstudio.sleeveapi.model.GridCategoryDO;
import com.robinstudio.sleeveapi.repository.GridCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GridCategoryService {
    @Autowired
    private GridCategoryRepository gridCategoryRepository;

    public List<GridCategoryDO> getGridCategoryList(){
        return gridCategoryRepository.findAll();
    }
}
