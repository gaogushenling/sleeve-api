package com.robinstudio.sleeveapi.service;

import com.robinstudio.sleeveapi.model.ThemeDO;
import com.robinstudio.sleeveapi.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThemeService {
    @Autowired
    private ThemeRepository themeRepository;

    public List<ThemeDO> findByNames(List<String> names) {
        return themeRepository.findByNames(names);
    }

    public Optional<ThemeDO> findByName(String name) {
        return themeRepository.findByName(name);

    }
}
