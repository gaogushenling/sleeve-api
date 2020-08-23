package com.robinstudio.sleeveapi.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Where(clause = "delete_time is null")
@Table(name = "theme", schema = "sleeve-api", catalog = "")
public class ThemeDO extends BaseEntity {
    @Id
    private Integer id;
    private String title;
    private String description;
    private String name;
    private String tplName; // 模版名称, 根据定义好的模版，前端已经提供了相应的多种模版（页面布局样式）供数据渲染，而不是写死的页面
    private String entranceImg;
    private String extend;
    private String internalTopImg;
    private String titleImg;
    private Boolean online;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "theme_spu", joinColumns = @JoinColumn(name = "theme_id"), inverseJoinColumns = @JoinColumn(name = "spu_id"))
    private List<SpuDO> spuList;
}
