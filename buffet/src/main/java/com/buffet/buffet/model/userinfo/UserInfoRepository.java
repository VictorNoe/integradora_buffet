package com.buffet.buffet.model.userinfo;

import com.buffet.buffet.model.usertype.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserInfoRepository extends JpaRepository<UserInfo, UUID> {
    int countUserInfoByFkUserType(UserType userType);
}
