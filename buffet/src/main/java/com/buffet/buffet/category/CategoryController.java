package com.buffet.buffet.category;

import com.buffet.buffet.category.categoryDTO.CategoryDTO;
import com.buffet.buffet.services.category.CategoryService;
import com.buffet.buffet.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping(value = "/addCategory", produces = "application/json")
    public ResponseEntity<CustomResponse> addCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.insertCategory(categoryDTO);
    }
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse> getAll(){
        return categoryService.getAll();
    }
}
