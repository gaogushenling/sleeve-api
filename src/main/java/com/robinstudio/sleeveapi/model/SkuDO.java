package com.robinstudio.sleeveapi.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.robinstudio.sleeveapi.util.GenericAndJson;
import com.robinstudio.sleeveapi.util.ListAndJson;
import com.robinstudio.sleeveapi.util.MapAndJson;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@Table(name = "sku", schema = "sleeve-api", catalog = "")
public class SkuDO extends BaseEntity {
    @Id
    private int id;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private byte online;
    private String img;
    private String title;
    private int spuId;


    private String code;
    private int stock;

    private Integer categoryId; // 冗余字段， sku -> spu -> category
    private Integer rootCategoryId; // 冗余字段

    @Convert(converter = MapAndJson.class)  // 测试单体Json -> Entity 映射
    private Map<String, Object> test;

    // private String specs;  // Object 类型报错

    // @Convert(converter = ListAndJson.class)  // 数组Json -> List Entity 映射
    // private List<Object> specs; // 冗余字段 查询了sku, 就不需要再查询规格名和规格值，减少了查询次数

    // 使用范型处理映射
    public List<SpecDO> getSpecs() {
        if (this.specs == null) {
            return Collections.emptyList();
        }
        // return GenericAndJson.jsonToList(this.specs, new TypeReference<List<SpecDO>>() {
        // });
        return GenericAndJson.jsonToObject(this.specs, new TypeReference<List<SpecDO>>() {
        });
    }

    public void setSpecs(List<SpecDO> specs) {
        if(specs.isEmpty()){
            return;
        }
        this.specs = GenericAndJson.objectToJson(specs);
    }

    private String specs;
}
