package com.robinstudio.sleeveapi.util;

import com.robinstudio.sleeveapi.bo.PageCounter;

public class CommonUtil {
    // 移动端分页参数start,count -> 传统分页参数 page, size
    public static PageCounter convertToPageParameter(Integer start, Integer count) {
        int pageNum = start / count;
        PageCounter pageCounter = PageCounter.builder()
                .page(pageNum)
                .count(count)
                .build();
        return pageCounter;
    }
}
