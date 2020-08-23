package com.robinstudio.sleeveapi.api.v1;

import com.robinstudio.sleeveapi.model.CategoryDO;
import com.robinstudio.sleeveapi.model.GridCategoryDO;
import com.robinstudio.sleeveapi.service.CategoryService;
import com.robinstudio.sleeveapi.service.GridCategoryService;
import com.robinstudio.sleeveapi.vo.CategoriesAllVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/category")
@Validated
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GridCategoryService gridCategoryService;

    @GetMapping("/all")
    public CategoriesAllVO getAll() {
        Map<Integer, List<CategoryDO>> categories = this.categoryService.getAll();
        return new CategoriesAllVO(categories);
    }

    @GetMapping("/grid/all")
    public List<GridCategoryDO> getGridCategoryList() {
        List<GridCategoryDO> gridCategoryDOList = gridCategoryService.getGridCategoryList();
        if (gridCategoryDOList.isEmpty()) {
            // throw new NotFoundException(30009)
        }
        return gridCategoryDOList;
    }
}
