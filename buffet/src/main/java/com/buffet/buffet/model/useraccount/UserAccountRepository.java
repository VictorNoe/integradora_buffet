package com.buffet.buffet.model.useraccount;

import com.buffet.buffet.model.usertype.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {
boolean existsByEmail(String email);
UserAccount findByEmail(String email);
List<UserAccount> findByFkUserInfo_FkUserType_UserType(String roleName);
int countUserAccountByFkUserInfo_FkUserType(UserType userType);
}
