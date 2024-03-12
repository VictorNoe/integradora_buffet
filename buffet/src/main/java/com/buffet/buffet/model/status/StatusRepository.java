package com.buffet.buffet.model.status;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StatusRepository extends JpaRepository<Status, UUID> {
    Optional<Status> findByStatusName(String status);

}
