package com.robinstudio.sleeveapi.vo;

import com.robinstudio.sleeveapi.model.CategoryDO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class CategoryPureVO {
    private Long id;
    private String name;
    private Boolean isRoot;
    private Integer parentId;
    private Integer index;
    private String img;

    public CategoryPureVO(CategoryDO categoryDO){
        BeanUtils.copyProperties(categoryDO, this);
    }
}
