package com.buffet.buffet.services.useraccount;
import com.buffet.buffet.controller.useraccount.useraccountdto.UserDTO;
import com.buffet.buffet.model.authrequest.AuthRequest;
import com.buffet.buffet.model.updatestatus.UpdateStatus;
import com.buffet.buffet.model.status.Status;
import com.buffet.buffet.model.status.StatusRepository;
import com.buffet.buffet.model.useraccount.UserAccount;
import com.buffet.buffet.model.useraccount.UserAccountRepository;
import com.buffet.buffet.model.userinfo.UserInfo;
import com.buffet.buffet.model.userinfo.UserInfoRepository;
import com.buffet.buffet.model.usertype.UserType;
import com.buffet.buffet.model.usertype.UserTypeRepository;
import com.buffet.buffet.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
@Slf4j
public class UserAccountServices {
    private final UserAccountRepository userAccountRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserTypeRepository userTypeRepository;
    private final StatusRepository statusRepository;
    @Autowired

    public UserAccountServices(UserAccountRepository userAccountRepository, UserInfoRepository userInfoRepository, UserTypeRepository userTypeRepository, StatusRepository statusRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userInfoRepository = userInfoRepository;
        this.userTypeRepository = userTypeRepository;
        this.statusRepository = statusRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> registerUser(UserDTO userdto) {
        try {
            log.info("UserDTO -> "+userdto.toString());
            UserAccount userAccountModel = new UserAccount();
            UserInfo userInfoModel = new UserInfo();
            if(userAccountRepository.existsByEmail(userdto.getEmail())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "El correo ya ah sido registrado"));
            }
            Optional<UserType> userType = userTypeRepository.findByUserType("Public");
            if(userType.isEmpty()){
                log.error("Tipo de usuario invalido");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Usuario invalido"));
            }
            Optional<Status> status = statusRepository.findByStatusName("enable");
            if(status.isPresent()){

                userInfoModel.setFkUserType(userType.get());
                userInfoModel.setName(userdto.getName());
                userInfoModel.setLastname(userdto.getLastname());
                userInfoModel.setSurname(userdto.getSurname());
                userInfoModel.setPhone(userdto.getPhone());
                userAccountModel.setFkUserInfo(userInfoRepository.save(userInfoModel));
                userAccountModel.setToken("public");
                userAccountModel.setEmail(userdto.getEmail());
                userAccountModel.setPassword(userdto.getPassword());
                userAccountModel.setFkStatus(status.get());
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new CustomResponse(userAccountRepository.save(userAccountModel), false, HttpStatus.CREATED.value(), "Usuario registrado"));
            }else {
                log.error("Status inexistente");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Status no encontrado"));

            }
        }catch (Exception e){
            log.error("Error al registrar usuario",e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponse(e, true,
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error intentando registrar usuario "+e.getMessage()));
        }

    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> registerWorker(UserDTO userdto) {
        try {
            log.info("UserDTO -> "+userdto.toString());
            UserAccount userAccountModel = new UserAccount();
            UserInfo userInfoModel = new UserInfo();
            if(userAccountRepository.existsByEmail(userdto.getEmail())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "El correo ya ah sido registrado"));
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
                userInfoModel.setName(userdto.getName());
                userInfoModel.setLastname(userdto.getLastname());
                userInfoModel.setPhone(userdto.getPhone());

                userAccountModel.setFkUserInfo(userInfoRepository.save(userInfoModel));
                userAccountModel.setToken("worker");
                userAccountModel.setEmail(userdto.getEmail());
                userAccountModel.setPassword(userdto.getPassword());
                userAccountModel.setFkStatus(status.get());
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new CustomResponse(userAccountRepository.save(userAccountModel), false, HttpStatus.CREATED.value(), "Usuario registrado correctamente"));
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
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> login(AuthRequest authRequest) {
        try {
            log.info("Auth request ->"+authRequest.toString());
            UserAccount optionalUserAccount = userAccountRepository.findByEmail(authRequest.getEmail());
            if (optionalUserAccount!=null) {
                boolean existsLogin = Objects.equals(optionalUserAccount.getPassword(), authRequest.getPassword());
                if (existsLogin) {
                    String accessToken="1234";
                    UserAccount ua = optionalUserAccount;
                    ua.setToken(accessToken);
                    userAccountRepository.save(ua);
                    return ResponseEntity.ok()
                            .header(HttpHeaders.AUTHORIZATION, "Bearer "+accessToken)
                            .body(new CustomResponse(userAccountRepository.save(ua), false, HttpStatus.OK.value(), "Sesión iniciada"));
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                            new CustomResponse(null, true, HttpStatus.UNAUTHORIZED.value(), "Credenciales inválidas"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CustomResponse(null, true,
                        HttpStatus.UNAUTHORIZED.value(), "El correo proporcionado no está registrado"));
            }
        } catch (Exception e) {
            log.error("Error al hacer login",e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CustomResponse(e, true, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error intentando hacer login"));
        }
    }
    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> getAllWorkers() {
        List<UserAccount> workersList = userAccountRepository.findByFkUserInfo_FkUserType_UserType("Worker");
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(workersList,false,200,"OK"));
    }
    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> getAllClients() {
        List<UserAccount> workersList = userAccountRepository.findByFkUserInfo_FkUserType_UserType("Public");
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(workersList,false,200,"OK"));
    }
    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> getInfoUser(String email) {
        if(!userAccountRepository.existsByEmail(email)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "email invalido"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(userAccountRepository.findByEmail(email),false,200,"OK"));
    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> updateStatus(UpdateStatus updateStatus){
        Optional<Status> statusExist = statusRepository.findByStatusName(updateStatus.getStatus());
        if (statusExist.isPresent()){
            UserAccount userUpdate = this.userAccountRepository.findByEmail(updateStatus.getName());
            if(userUpdate==null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "Usuario invalido"));
            }
            userUpdate.setFkStatus(statusExist.get());
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(userAccountRepository.saveAndFlush(userUpdate),false,200,"Usuario actualizado"));
        }else {
            log.error("Status inexistente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Status invalido"));
        }
    }
}
