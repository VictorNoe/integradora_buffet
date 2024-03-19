package com.buffet.buffet.model.worker;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkerRepository extends JpaRepository<Worker, UUID> {
    Worker findByNumWorker(String numWorker);
    boolean existsByNumWorker(String numWorker);
}
