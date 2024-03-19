package com.buffet.buffet.model.packageimage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PackageImageRepository extends JpaRepository<PackageImage, UUID> {
        List<PackageImage> findAllByServicePackage_PackageName(String packageName);
}
