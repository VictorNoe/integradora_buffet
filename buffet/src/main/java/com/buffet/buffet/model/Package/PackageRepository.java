package com.buffet.buffet.model.Package;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PackageRepository extends JpaRepository<Package, UUID> {
Package findByPackageName(String name);


}
