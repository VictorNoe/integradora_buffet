package com.buffet.buffet.services.category;

import com.buffet.buffet.controller.category.categoryDTO.CategoryDTO;
import com.buffet.buffet.model.category.Category;
import com.buffet.buffet.model.category.CategoryRepository;
import com.buffet.buffet.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Transactional
@Service
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> getAll() {

        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(this.categoryRepository.findAll(),false,200,"OK"));
    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> insertCategory(CategoryDTO categoryDTO){

        Optional<Category> existName = categoryRepository.findByCategoryName(categoryDTO.getCategory_name());
        if(existName.isPresent()){
            log.error("Nombre de categoria ya existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "El nombre de la categoria ya existe"));
        }
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategory_name());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CustomResponse(categoryRepository.save(category), false, HttpStatus.CREATED.value(), "Categoria registrada"));
    }
}
