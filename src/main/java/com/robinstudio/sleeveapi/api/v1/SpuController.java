package com.robinstudio.sleeveapi.api.v1;

import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.robinstudio.sleeveapi.bo.PageCounter;
import com.robinstudio.sleeveapi.model.SpuDO;
import com.robinstudio.sleeveapi.service.SpuService;
import com.robinstudio.sleeveapi.util.CommonUtil;
import com.robinstudio.sleeveapi.vo.Paging;
import com.robinstudio.sleeveapi.vo.PagingDozer;
import com.robinstudio.sleeveapi.vo.SpuSimplifyVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/spu")
@Validated
public class SpuController {
    @Autowired
    public SpuService spuService;

    // 获取 SPU 详情数据
    @GetMapping("/{id}/detail")
    public SpuDO getSpuDetail(@PathVariable @Positive Long id) {
        SpuDO spuDO = this.spuService.getSpu(id);
        if (spuDO == null) {
            // throw new NotFoundException(30003)
        }
        return spuDO;
    }

    // 获取 SPU 简化信息
    @GetMapping("/{id}/simplify")
    public SpuSimplifyVO getSpuSimplify(@PathVariable @Positive(message="{id.positive}") Long id) {
        SpuDO spuDO = this.spuService.getSpu(id);
        SpuSimplifyVO vo = new SpuSimplifyVO();
        BeanUtils.copyProperties(spuDO, vo);
        return vo;
    }

    // 获取最新的 SPU 列表数据，分页，返回概述信息
    // @GetMapping("/latest")
    // public List<SpuDO> getLatestSpuList() {
    //     return this.spuService.getLatestPagingSpu();
    // }
    @GetMapping("/latest")
    public PagingDozer<SpuDO, SpuSimplifyVO> getLatestSpuList(@RequestParam(defaultValue = "0") Integer start, @RequestParam(defaultValue = "10") Integer count) {
        // Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        // List<SpuSimplifyVO> voList = new ArrayList<>();
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start, count);
        // Page<SpuDO> spuDOList = this.spuService.getLatestPagingSpu(pageCounter.getPage(), pageCounter.getCount());
        // Paging<SpuDO> paging = new Paging<>(spuDOList);
        // paging.getItems().forEach(s -> {
        //     SpuSimplifyVO vo = mapper.map(s, SpuSimplifyVO.class);
        //     voList.add(vo);
        // });
        // return voList;
        Page<SpuDO> page = this.spuService.getLatestPagingSpu(pageCounter.getPage(), pageCounter.getCount());
        return new PagingDozer<>(page, SpuSimplifyVO.class);
    }

    // 获取分类spu 前端传参下划线 后端小驼峰 is_root 判断是否是根节点
    @GetMapping("/by/category/{id}")
    public PagingDozer<SpuDO, SpuSimplifyVO> getByCategoryId(@PathVariable @Positive(message="{id.positive}") Long id,
                                                             @RequestParam(name = "is_root", defaultValue = "false") Boolean isRoot,
                                                             @RequestParam(defaultValue = "0") Integer start,
                                                             @RequestParam(defaultValue = "10") Integer count) {
        PageCounter pageCounter = CommonUtil.convertToPageParameter(start, count);
        Page<SpuDO> page = this.spuService.getByCategoryId(id, isRoot, pageCounter.getPage(), pageCounter.getCount());
        return new PagingDozer<>(page, SpuSimplifyVO.class);
    }
}
