package com.buffet.buffet.controller.worker;

import com.buffet.buffet.controller.worker.workerdto.WorkerDto;
import com.buffet.buffet.services.worker.WorkerService;
import com.buffet.buffet.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/worker")
@CrossOrigin(origins = {"http://localhost:5173/"})
public class WorkerController {
    private final WorkerService workerService;
    @Autowired

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }
    @PostMapping(value = "/registerWorker", produces = "application/json")
    public ResponseEntity<CustomResponse> registerWorker(@Valid @RequestBody WorkerDto workerDto) {
        return workerService.registerWorker(workerDto);
    }
}
