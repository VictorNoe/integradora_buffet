package com.buffet.buffet.model.usertype;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTypeRepository extends JpaRepository<UserTypeModel,Integer> {
    public Optional<UserTypeModel> findByUserType(String userType);

}
