package com.buffet.buffet.services.auth;

import com.buffet.buffet.model.authrequest.AuthRequest;
import com.buffet.buffet.model.useraccount.UserAccount;
import com.buffet.buffet.model.useraccount.UserAccountRepository;
import com.buffet.buffet.model.worker.Worker;
import com.buffet.buffet.model.worker.WorkerRepository;
import com.buffet.buffet.security.service.JwtService;
import com.buffet.buffet.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;

@Transactional
@Service
@Slf4j
public class AuthService {
    private final UserAccountRepository userAccountRepository;
    private final WorkerRepository workerRepository;
    private final AuthenticationManager manager;
    private final JwtService provider;
    @Autowired

    public AuthService(UserAccountRepository userAccountRepository, WorkerRepository workerRepository, AuthenticationManager manager, JwtService provider) {
        this.userAccountRepository = userAccountRepository;
        this.workerRepository = workerRepository;
        this.manager = manager;
        this.provider = provider;
    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> login(AuthRequest authRequest) {
        try {
            UserAccount userAccount = userAccountRepository.findByEmail(authRequest.getUsername());
            Worker worker = workerRepository.findByNumWorker(authRequest.getUsername());
            if (userAccount != null || worker != null) {
                String token = authentication(authRequest);
                if (token == null) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                            new CustomResponse(null, true, HttpStatus.UNAUTHORIZED.value(), "Credenciales invalidas"));
                }
                if (worker == null) {
                    userAccount.setToken(token);
                    userAccount.setLoginTime(new Date());
                    userAccountRepository.save(userAccount);

                }
                return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.AUTHORIZATION, token).body(
                        new CustomResponse(token, false, HttpStatus.OK.value(), "Login exitoso"));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                        new CustomResponse(null, true, HttpStatus.UNAUTHORIZED.value(), "Credenciales invalidas"));
            }
        } catch (Exception e) {
            log.error("Error al hacer login", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CustomResponse(e, true, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error intentando hacer login"));
        }
    }

    private String authentication(AuthRequest authRequest) {
        try {
            Authentication auth = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = provider.generateToken(auth);
            return token;
        } catch (BadCredentialsException e) {
            log.error("Credenciales invalidas");
            return null;
        }
    }

}
