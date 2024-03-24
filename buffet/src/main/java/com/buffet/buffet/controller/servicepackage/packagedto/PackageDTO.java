package com.buffet.buffet.controller.servicepackage.packagedto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class PackageDTO {
    @Pattern(regexp = "^[a-zA-Z0-9ñÑ\\s ]+$", message = "El nombre no debe contener caracteres especiales")
    @NotEmpty(message = "El nombre no puede estar en blanco")
    @Length(max = 100, message = "El nombre no debe tener más de 100 caracteres")
    private String packageName;

    @Pattern(regexp = "^[a-zA-Z0-9ñÑ\\s ]+$", message = "La descripción no debe contener caracteres especiales")
    @NotEmpty(message = "La descripción no puede estar en blanco")
    @Length(max = 300, message = "La descripción no debe tener más de 300 caracteres")
    private String packageDescription;
    @NotNull(message = "El precio no puede estar vacio")
    private double price;
    private double discount;
    private Integer ability;
    @NotNull(message = "Debes agregar una categoria")
    private String category;
    private ImageDTO[] images;

}