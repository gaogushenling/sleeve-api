package com.robinstudio.sleeveapi.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "category", schema = "sleeve-api", catalog = "")
public class CategoryDO extends Serializers.Base {
    @Id
    private Long id;
    private String name;
    private String description;
    private Boolean isRoot;
    private Integer parentId;
    private String img;
    private Integer index;
    private Integer online;
    private Integer level;
}
