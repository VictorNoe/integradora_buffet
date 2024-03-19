package com.buffet.buffet.controller.servicepackage.packagedto;

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
    @Pattern(regexp = "^[a-zA-Z0-9\\s ]+$", message = "El nombre no debe contener caracteres especiales")
    @NotEmpty(message = "El nombre no puede estar en blanco")
    private String packageName;

    @Pattern(regexp = "^[a-zA-Z0-9\\s ]+$", message = "La descripcion no debe contener caracteres especiales")
    @NotEmpty(message = "La descripcion no puede estar en blanco")
    private String packageDescription;
    @NotNull(message = "El precio no puede estar vacio")
    private double price;
    private double discount;
    private Integer ability;
    @NotNull(message = "Debes agregar una categoria")
    private String category;
    private ImageDTO[] images;

}