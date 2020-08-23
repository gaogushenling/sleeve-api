package com.robinstudio.sleeveapi.api.v1;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.robinstudio.sleeveapi.exception.http.NotFoundException;
import com.robinstudio.sleeveapi.model.ThemeDO;
import com.robinstudio.sleeveapi.service.ThemeService;
import com.robinstudio.sleeveapi.vo.ThemePureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequestMapping("/v1/theme")
@RestController
@Validated
public class ThemeController {
    @Autowired
    private ThemeService themeService;

    @GetMapping("/by/names")
    public List<ThemePureVO> getThemeGroupByNames(@RequestParam(name = "names") String names) {
        List<String> nameList = Arrays.asList(names.split(","));
        List<ThemeDO> themes = themeService.findByNames(nameList);
        List<ThemePureVO> list = new ArrayList<>();
        themes.forEach(theme -> {
            Mapper mapper = DozerBeanMapperBuilder.buildDefault();
            ThemePureVO vo = mapper.map(theme, ThemePureVO.class);
            list.add(vo);
        });
        return list;
    }

    @GetMapping("/name/{name}/with_spu")
    public ThemeDO getThemeByNameWithSpu(@PathVariable(name = "name") String themeName) {
        Optional<ThemeDO> optionalThemeDO = this.themeService.findByName(themeName);
        return optionalThemeDO.orElseThrow(() ->
                new NotFoundException(30003)
        );
    }
}
