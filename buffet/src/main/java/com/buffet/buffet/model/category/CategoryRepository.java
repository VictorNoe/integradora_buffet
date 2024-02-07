package com.buffet.buffet.model.category;

import com.buffet.buffet.model.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByCategoryName(String name);

}
