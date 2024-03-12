package com.buffet.buffet.controller.category;

import com.buffet.buffet.controller.category.categoryDTO.CategoryDTO;
import com.buffet.buffet.services.category.CategoryService;
import com.buffet.buffet.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = {"*"})
public class CategoryController {

    private final CategoryService categoryService;
    @Autowired

    public CategoryController(CategoryService _categoryService) {
        this.categoryService = _categoryService;
    }

    @PostMapping(value = "/addCategory", produces = "application/json")
    public ResponseEntity<CustomResponse> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryService.insertCategory(categoryDTO);
    }
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse> getAll(){
        return categoryService.getAll();
    }
}
