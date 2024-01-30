package com.buffet.buffet.services.userinfo;

import com.buffet.buffet.model.userinfo.UserInfoModel;
import com.buffet.buffet.model.userinfo.UserInfoRepository;
import com.buffet.buffet.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserInfoService {
    @Autowired
    private UserInfoRepository repository;
    public ResponseEntity<CustomResponse> getAll() {

            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(this.repository.findAll(),false,200,"OK"));
    }
}
