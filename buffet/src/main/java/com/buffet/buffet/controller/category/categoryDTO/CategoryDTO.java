package com.buffet.buffet.controller.category.categoryDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class CategoryDTO {

    private Integer id;
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El nombre no debe contener caracteres especiales")
    @NotEmpty(message = "El nombre no puede estar en blanco")
    private String categoryName;
}
