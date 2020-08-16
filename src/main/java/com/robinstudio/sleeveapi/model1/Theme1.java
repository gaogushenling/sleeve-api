package com.robinstudio.sleeveapi.model1;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Theme1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String name;

    @ManyToMany
    @JoinTable(name = "theme_spu", joinColumns = @JoinColumn(name = "theme_id"), inverseJoinColumns = @JoinColumn(name = "spu_id"))
    private List<Spu1> spuList;
}
