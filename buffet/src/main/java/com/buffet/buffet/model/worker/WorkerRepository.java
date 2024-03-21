package com.buffet.buffet.model.worker;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WorkerRepository extends JpaRepository<Worker, UUID> {
    Worker findByNumWorker(String numWorker);
    boolean existsByNumWorker(String numWorker);
    List<Worker> findByFkUserInfo_FkUserType_TypeName(String roleName);
    int countWorkerByFkUserInfo_FkUserType_TypeName(String roleName);

}
