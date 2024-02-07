package com.buffet.buffet.services.useraccount;
import com.buffet.buffet.controller.userAccount.userAccountDTO.UserDTO;
import com.buffet.buffet.model.AuthRequest.AuthRequest;
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
import java.util.Objects;
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
            UserAccount userAccountModel = new UserAccount();
            UserInfo userInfoModel = new UserInfo();
            if(userAccountRepository.existsByEmail(userdto.getEmail())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "El correo ya ah sido registrado"));
            }
            Optional<UserType> userType = userTypeRepository.findByUserType(userdto.getUserType());
            if(userType.isEmpty()){
                log.error("Tipo de usuario invalido");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Tipo de usuario invalido"));
            }
            Optional<Status> status = statusRepository.findByStatus("enable");
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
                    HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error intentando registrar usuario "+e.getMessage()));
        }

    }
    public ResponseEntity<CustomResponse> login(AuthRequest authRequest) {
        try {
            log.info("Auth request ->"+authRequest.toString());
            Optional<UserAccount> optionalUserAccount = userAccountRepository.findByEmail(authRequest.getEmail());
            if (optionalUserAccount.isPresent()) {
                boolean existsLogin = Objects.equals(optionalUserAccount.get().getPassword(), authRequest.getPassword());
                if (existsLogin) {
                    String accessToken="1234";
                    UserAccount ua = optionalUserAccount.get();
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
}
