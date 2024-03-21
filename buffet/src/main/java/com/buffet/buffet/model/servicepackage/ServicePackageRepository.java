package com.buffet.buffet.model.servicepackage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServicePackageRepository extends JpaRepository<ServicePackage, UUID> {
ServicePackage findByPackageName(String name);
int countServicePackageByStatus_StatusNameAndStatus_StatusDescription(String status,String description);
}
