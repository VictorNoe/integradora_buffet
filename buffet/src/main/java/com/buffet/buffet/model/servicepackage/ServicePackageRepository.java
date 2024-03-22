package com.buffet.buffet.model.servicepackage;

import com.buffet.buffet.model.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServicePackageRepository extends JpaRepository<ServicePackage, UUID> {
ServicePackage findByPackageName(String name);

List<ServicePackage> findByStatus(Status status);
int countServicePackageByStatus_StatusNameAndStatus_StatusDescription(String status,String description);
}
