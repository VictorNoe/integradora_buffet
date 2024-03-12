package com.buffet.buffet.services.userinfo;

import com.buffet.buffet.model.userinfo.UserInfoRepository;
import com.buffet.buffet.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserInfoService {
    private final UserInfoRepository repository;
    @Autowired

    public UserInfoService(UserInfoRepository _repository) {
        this.repository = _repository;
    }

    public ResponseEntity<CustomResponse> getAll(Pageable page) {

            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(this.repository.findAll(page),false,200,"OK"));
    }
}
