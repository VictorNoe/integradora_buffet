package com.buffet.buffet.services.worker;

import com.buffet.buffet.controller.worker.workerdto.WorkerDto;
import com.buffet.buffet.model.status.Status;
import com.buffet.buffet.model.status.StatusRepository;
import com.buffet.buffet.model.userinfo.UserInfo;
import com.buffet.buffet.model.userinfo.UserInfoRepository;
import com.buffet.buffet.model.usertype.UserType;
import com.buffet.buffet.model.usertype.UserTypeRepository;
import com.buffet.buffet.model.worker.Worker;
import com.buffet.buffet.model.worker.WorkerRepository;
import com.buffet.buffet.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Slf4j
public class WorkerService {
    private final WorkerRepository workerRepository;
    private final StatusRepository statusRepository;
    private final UserTypeRepository userTypeRepository;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository,StatusRepository statusRepository,
                         UserTypeRepository userTypeRepository, UserInfoRepository userInfoRepository) {
        this.workerRepository = workerRepository;
        this.statusRepository=statusRepository;
        this.userTypeRepository = userTypeRepository;
        this.userInfoRepository = userInfoRepository;
    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> registerWorker(WorkerDto workerDto) {
        try {
            Worker worker = new Worker();
            UserInfo userInfoModel = new UserInfo();
            if(workerRepository.existsByNumWorker(workerDto.getNumWorker())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "El numero de trabajador ya ah sido registrado"));
            }
            Optional<UserType> userType = userTypeRepository.findByUserType("Worker");
            if(userType.isEmpty()){
                log.error("Usuario invalido");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Tipo de usuario invalido"));
            }
            Optional<Status> status = statusRepository.findByStatusName("enable");
            if(status.isPresent()){

                userInfoModel.setFkUserType(userType.get());
                userInfoModel.setName(workerDto.getName());
                userInfoModel.setLastname(workerDto.getLastname());
                userInfoModel.setPhone(workerDto.getPhone());
                worker.setFkUserInfo(userInfoRepository.save(userInfoModel));
                worker.setNumWorker(worker.getNumWorker());
                worker.setWorkerPassword(workerDto.getWorkerPassword());
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new CustomResponse(workerRepository.save(worker), false, HttpStatus.CREATED.value(), "Trabajador registrado correctamente"));
            }else {
                log.error("Status inexistente");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Status invalido"));

            }
        }catch (Exception e){
            log.error("Error al registrar usuario",e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse(e, true,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error intentando registrar usuario "+e.getMessage()));
        }

    }
}
