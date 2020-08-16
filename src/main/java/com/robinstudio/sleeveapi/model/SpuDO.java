package com.robinstudio.sleeveapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "spu")
public class SpuDO extends BaseEntity {
    @Id
    private Long id;
    private String title;
    private String subtitle;
    private Long categoryId;
    private Long rootCategoryId;
    private boolean online;
    private String price;
    private Long sketchSpecId;
    private Long defaultSkuId;
    private String img;
    private String discountPrice;
    private String description;
    private String tags;
    private boolean isTest;
    // private Object spuThemeImg;
    private String forThemeImg;

    private Date createTime;

    @OneToMany(fetch = FetchType.LAZY)  // 默认是懒加载，可以省略
    @JoinColumn(name = "spuId")
    private List<SkuDO> skuDOList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "spuId")
    private List<SpuImgDO> spuImgDOList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "spuId")
    private List<SpuDetailImgDO> spuDetailImgDOList;
}
