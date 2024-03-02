package com.buffet.buffet.model.Package;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PackageRepository extends JpaRepository<Package, UUID> {
Optional<Package> findByPackageName(String name);
}
