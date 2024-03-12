package com.buffet.buffet.services.assigment;

import com.buffet.buffet.controller.assignment.assignmentdto.AssignmentDTO;
import com.buffet.buffet.model.assigment.WorkerAssignment;
import com.buffet.buffet.model.assigment.WorkerAssignmentRepository;
import com.buffet.buffet.model.orders.Order;
import com.buffet.buffet.model.orders.OrderRepository;
import com.buffet.buffet.model.useraccount.UserAccount;
import com.buffet.buffet.model.useraccount.UserAccountRepository;
import com.buffet.buffet.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Slf4j
public class WorkerAssignmentService {
   private final WorkerAssignmentRepository workerAssignmentRepository;
   private final UserAccountRepository userAccountRepository;
   private final OrderRepository orderRepository;
    @Autowired

    public WorkerAssignmentService(WorkerAssignmentRepository workerAssignmentRepository, UserAccountRepository userAccountRepository, OrderRepository orderRepository) {
        this.workerAssignmentRepository = workerAssignmentRepository;
        this.userAccountRepository = userAccountRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> assignmentOrder(AssignmentDTO assignmentDTO){
        Order existOrder = this.orderRepository.findByNumOrder(assignmentDTO.getNumOrder());
        if (existOrder!=null){
            UserAccount existUser = this.userAccountRepository.findByEmail(assignmentDTO.getUserEmail());
            if (existUser!=null){
                WorkerAssignment workerAssignmentSaved = new WorkerAssignment();
                workerAssignmentSaved.setPackageOrder(existOrder);
                workerAssignmentSaved.setUserAccount(existUser);
                return ResponseEntity.status(HttpStatus.CREATED).body(new CustomResponse(this.workerAssignmentRepository.save(workerAssignmentSaved),false,HttpStatus.CREATED.value(), "Orden asignada"));
            }else {
                log.error("No existe el usuario: "+assignmentDTO.getUserEmail());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(null,true,HttpStatus.NOT_FOUND.value(), "Usuario invalido"));
            }
        }else {
            log.error("No existe un paquete con el numero de orden: "+assignmentDTO.getNumOrder());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(null,true,HttpStatus.NOT_FOUND.value(), "Numero de orden invalido"));
        }
    }
    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> getAssignByUser(String email){
       UserAccount existUser = this.userAccountRepository.findByEmail(email);
       if (existUser!=null){
           List<WorkerAssignment> assignmentList = this.workerAssignmentRepository.findByUserAccount_Email(existUser.getEmail());
           return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(assignmentList,false,HttpStatus.OK.value(), "Lista de asignaciones de usuario "+existUser.getEmail()));
       }else {
           log.error("No existe el usuario: "+ email);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(null,true,HttpStatus.NOT_FOUND.value(), "Usuario invalido"));
       }
    }
}
