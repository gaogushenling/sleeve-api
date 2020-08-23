package com.robinstudio.sleeveapi.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Where(clause = "delete_time is null")
@Table(name = "grid_category", schema = "sleeve-api", catalog = "")
public class GridCategoryDO extends BaseEntity{
    @Id
    private Long id;
    private String title;
    private String img;
    private String name;
    private Integer categoryId;
    private Integer rootCategoryId;
}
