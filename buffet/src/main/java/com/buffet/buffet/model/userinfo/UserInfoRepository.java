package com.buffet.buffet.model.userinfo;

import com.buffet.buffet.model.userinfo.UserInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfoModel,Integer> {

}
