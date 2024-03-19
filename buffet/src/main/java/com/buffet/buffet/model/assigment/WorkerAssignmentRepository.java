package com.buffet.buffet.model.assigment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WorkerAssignmentRepository extends JpaRepository<WorkerAssignment, UUID> {
    List<WorkerAssignment> findByWorker_NumWorker(String numWorker);

}
