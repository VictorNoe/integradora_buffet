package com.buffet.buffet.model.assigment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkerAssignmentRepository extends JpaRepository<WorkerAssignment, UUID> {
}
