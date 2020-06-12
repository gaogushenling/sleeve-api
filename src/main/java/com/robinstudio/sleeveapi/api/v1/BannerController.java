package com.robinstudio.sleeveapi.api.v1;

import com.robinstudio.sleeveapi.model.Banner;
import com.robinstudio.sleeveapi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/v1/banner")
@Validated
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("/name/{name}")
    public Banner getByName(@PathVariable @NotBlank String name) {
        Banner banner = bannerService.getByName(name);
        return banner;
    }
}
