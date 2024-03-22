package com.buffet.buffet.model.packagehascomment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PackageHasCommentRepository extends JpaRepository<PackageHasComment, UUID> {

    List<PackageHasComment> findByServicePackagePackageName(String packageName);

}
