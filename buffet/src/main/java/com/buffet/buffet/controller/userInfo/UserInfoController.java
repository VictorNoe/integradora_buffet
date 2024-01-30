package com.buffet.buffet.controller.userInfo;

import com.buffet.buffet.model.userinfo.UserInfoModel;
import com.buffet.buffet.services.userinfo.UserInfoService;
import com.buffet.buffet.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/userInfo")
@CrossOrigin(origins = {"*"})
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse> getAll(){
        return this.userInfoService.getAll();
    }
}
