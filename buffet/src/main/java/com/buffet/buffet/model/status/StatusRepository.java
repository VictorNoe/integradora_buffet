package com.buffet.buffet.model.status;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status,Integer> {
    Optional<Status> findByStatus(String status);

}
