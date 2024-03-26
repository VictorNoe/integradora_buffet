package com.buffet.buffet.controller.worker;

import com.buffet.buffet.controller.worker.workerdto.WorkerDto;
import com.buffet.buffet.model.updatestatus.UpdateStatus;
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
        return this.workerService.registerWorker(workerDto);
    }
    @PutMapping(value = "/updateStatus", produces = "application/json")
    public ResponseEntity<CustomResponse> updateStatus(@Valid @RequestBody UpdateStatus updateStatus){
        return workerService.updateStatusWorker(updateStatus);
    }
    @GetMapping(value = "/getWorkers", produces = "application/json")
    public ResponseEntity<CustomResponse> getAllWorkers( ){
        return this.workerService.getAllWorkers();
    }
    @GetMapping(value = "/getCountWorkers", produces = "application/json")
    public ResponseEntity<CustomResponse> getCountWorkers( ){
        return this.workerService.getCountWorkers();
    }
}
