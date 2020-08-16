package com.robinstudio.sleeveapi.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Paging<T> {
    private Long total;
    private Integer page;
    private Integer count;
    private Integer totalPage;
    private List<T> items;

    public Paging(Page<T> pageT){
        this.initPageParameters(pageT);
        this.items = pageT.getContent();
    }

    void initPageParameters(Page<T> pageT){
        this.total = pageT.getTotalElements();
        this.page = pageT.getSize();
        this.count = pageT.getNumber();
        this.totalPage = pageT.getTotalPages();
    }
}
