package com.robinstudio.sleeveapi.model1;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Spu1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subtitle;

//    @ManyToMany
//    private List<Theme> themeList;
}
