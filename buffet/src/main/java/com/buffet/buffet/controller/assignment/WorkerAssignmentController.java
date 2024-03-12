package com.buffet.buffet.controller.assignment;

import com.buffet.buffet.controller.assignment.assignmentDto.AssignmentDTO;
import com.buffet.buffet.services.assigment.WorkerAssignmentService;
import com.buffet.buffet.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workerAssignment")
@CrossOrigin(origins = {"*"})
public class WorkerAssignmentController {
    private final WorkerAssignmentService workerAssignmentService;
    @Autowired
    public WorkerAssignmentController(WorkerAssignmentService workerAssignmentService) {
        this.workerAssignmentService = workerAssignmentService;
    }
    @PostMapping(value = "/assignOrder", produces = "application/json")
    public ResponseEntity<CustomResponse> register(@Valid @RequestBody AssignmentDTO assignmentDTO) {
        return workerAssignmentService.assignmentOrder(assignmentDTO);
    }
    @PostMapping(value = "/getAssignByUser", produces = "application/json")
    public ResponseEntity<CustomResponse> getAssignByUser(@RequestParam String email ){
        return this.workerAssignmentService.getAssignByUser(email);
    }}
