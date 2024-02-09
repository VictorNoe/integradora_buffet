package com.buffet.buffet.controller.Package.PackageDTO;

import com.buffet.buffet.model.category.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class PackageDTO {
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El nombre no debe contener caracteres especiales")
    @NotEmpty(message = "El nombre no puede estar en blanco")
    private String packageName;
    @NotEmpty(message = "La imagen no puede estar vacia")
    private String image;
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "La descripcion no debe contener caracteres especiales")
    @NotEmpty(message = "La descripcion no puede estar en blanco")
    private String packageDescription;
    @NotNull(message = "El precio no puede estar vacio")
    private double price;
    private double discount;
    @NotNull(message = "Debes agregar una categoria")
    private Category category;


}
