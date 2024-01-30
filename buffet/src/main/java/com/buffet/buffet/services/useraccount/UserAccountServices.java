package com.buffet.buffet.services.useraccount;
import com.buffet.buffet.controller.userAccount.userAccountDTO.UserDTO;
import com.buffet.buffet.model.Status.StatusModel;
import com.buffet.buffet.model.Status.StatusRepository;
import com.buffet.buffet.model.useraccount.UserAccountModel;
import com.buffet.buffet.model.useraccount.UserAccountRepository;
import com.buffet.buffet.model.userinfo.UserInfoModel;
import com.buffet.buffet.model.userinfo.UserInfoRepository;
import com.buffet.buffet.model.usertype.UserTypeModel;
import com.buffet.buffet.model.usertype.UserTypeRepository;
import com.buffet.buffet.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserAccountServices {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserTypeRepository userTypeRepository;
    @Autowired
    private StatusRepository statusRepository;

    public ResponseEntity<CustomResponse> registerUser(UserDTO userdto) {
        try {
            log.info("UserDTO -> "+userdto.toString());
            UserAccountModel userAccountModel = new UserAccountModel();
            UserInfoModel userInfoModel = new UserInfoModel();
            if(userAccountRepository.existsByEmail(userdto.getEmail())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "El correo ya ah sido registrado"));
            }
            Optional<UserTypeModel> userType = userTypeRepository.findByUserType(userdto.getUserType());
            if(userType.isEmpty()){
                log.error("Tipo de usuario invalido");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Tipo de usuario invalido"));
            }
            Optional<StatusModel> status = statusRepository.findByStatus("enable");
            if(status.isPresent()){

                userInfoModel.setFkUserType(userType.get());
                userInfoModel.setName(userdto.getName());
                userInfoModel.setLastname(userdto.getLastname());

                userAccountModel.setFkUserInfo(userInfoRepository.save(userInfoModel));
                userAccountModel.setToken("test");
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
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error intentando registrar usuario "+e.getMessage().toString()));
        }

    }
}
