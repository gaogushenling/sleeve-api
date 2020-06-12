package com.robinstudio.sleeveapi.service;

import com.robinstudio.sleeveapi.model.Banner;
import com.robinstudio.sleeveapi.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerRepository bannerRepository;

    public Banner getByName(String name) {
        return bannerRepository.findOneByName(name);
    }
}
