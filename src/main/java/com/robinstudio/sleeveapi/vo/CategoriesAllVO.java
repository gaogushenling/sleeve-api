package com.robinstudio.sleeveapi.vo;

import com.robinstudio.sleeveapi.model.CategoryDO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class CategoriesAllVO {
    private List<CategoryPureVO> roots;
    private List<CategoryPureVO> subs;

    public CategoriesAllVO(Map<Integer, List<CategoryDO>> map) {
        // List<CategoryDO> roots = map.get(1);
        // roots.forEach(categoryDO -> {
        //     CategoryPureVO vo = new CategoryPureVO(categoryDO);
        //     this.roots.add(vo);
        // });

        this.roots = map.get(1).stream() // 转化 stream 可以使用 lambda 方法
                .map(CategoryPureVO::new).collect(Collectors.toList()); // method reference 写法相当于 map(r->{return new DO(r)})
        this.subs = map.get(2).stream()
                .map(CategoryPureVO::new).collect(Collectors.toList());
    }
}
