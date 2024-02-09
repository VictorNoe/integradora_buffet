package com.buffet.buffet.model.Package;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package,Integer> {
Optional<Package> findByPackageName(String name);
}
