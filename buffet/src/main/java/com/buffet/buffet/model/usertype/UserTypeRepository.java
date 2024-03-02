package com.buffet.buffet.model.usertype;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserTypeRepository extends JpaRepository<UserType, UUID> {
    public Optional<UserType> findByUserType(String userType);

}
