package com.buffet.buffet.model.package_has_comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PackageHasCommentRepository extends JpaRepository<PackageHasComment, UUID> {

    @Query(value = "SELECT * FROM package_has_comment WHERE fk_package = ?1", nativeQuery = true)
    List<PackageHasComment> findByPackage(UUID id);

}
