package com.buffet.buffet.model.Status;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<StatusModel,Integer> {
    Optional<StatusModel> findByStatus(String status);

}
