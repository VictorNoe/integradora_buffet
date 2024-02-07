package com.buffet.buffet.services.Package;

import com.buffet.buffet.model.Package.PackageRepository;
import com.buffet.buffet.model.category.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PackageService {
    @Autowired
    PackageRepository packageRepository;
    @Autowired
    CategoryRepository categoryRepository;
}
