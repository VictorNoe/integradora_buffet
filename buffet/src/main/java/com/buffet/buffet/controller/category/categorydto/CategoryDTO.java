package com.buffet.buffet.controller.category.categorydto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class CategoryDTO {

    private Integer id;
    @Pattern(regexp = "^[a-zA-ZñÑ ]+$", message = "El nombre no debe contener caracteres especiales")
    @NotEmpty(message = "El nombre no puede estar en blanco")
    @Length(max = 50, message = "El nombre no debe tener más de 50 caracteres")
    private String categoryName;
}
