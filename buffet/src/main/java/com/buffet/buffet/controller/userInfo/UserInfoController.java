package com.buffet.buffet.controller.userInfo;

import com.buffet.buffet.services.userinfo.UserInfoService;
import com.buffet.buffet.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
@RequestMapping("/api/userInfo")
@CrossOrigin(origins = {"*"})
public class UserInfoController {
    private final UserInfoService userInfoService;
    @Autowired

    public UserInfoController(UserInfoService _userInfoService) {
        this.userInfoService = _userInfoService;
    }

    @PostMapping(value = "/pageUsers", produces = "application/json")
    public ResponseEntity<CustomResponse> getAll(Pageable page){
        return this.userInfoService.getAll(page);
    }
}
