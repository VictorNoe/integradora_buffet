package com.buffet.buffet.model.useraccount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {
boolean existsByEmail(String email);
Optional<UserAccount> findByEmail(String email);
List<UserAccount> findByFkUserInfo_FkUserType_UserType(String roleName);

}
