package com.buffet.buffet.model.useraccount;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccountModel,Integer> {
boolean existsByEmail(String email);
}
