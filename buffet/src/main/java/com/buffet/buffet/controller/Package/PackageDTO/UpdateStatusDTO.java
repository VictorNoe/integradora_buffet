package com.buffet.buffet.controller.Package.PackageDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class UpdateStatusDTO {
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El nombre no debe contener caracteres especiales")
    @NotEmpty(message = "El nombre no puede estar en blanco")
    private String packageName;
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El status no debe contener caracteres especiales")
    @NotEmpty(message = "El status no puede estar en blanco")
    private String status;
}
