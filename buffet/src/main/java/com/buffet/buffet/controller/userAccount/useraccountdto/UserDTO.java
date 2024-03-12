package com.buffet.buffet.controller.userAccount.useraccountdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class UserDTO {

    private Integer id;
    @Email(message = "Correo electrónico no válido")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@gmail\\.[a-zA-Z]{2,}$", message = "El correo electrónico debe ser de Gmail")
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "La contraseña no puede estar en blanco")
    @Length(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El nombre no debe contener caracteres especiales")
    @NotEmpty(message = "El nombre es requerido")
    private String name;
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El apellido no debe contener caracteres especiales")
    @NotEmpty(message = "El apellido es requerido")
    private String lastname;
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El apellido no debe contener caracteres especiales")
    @NotEmpty(message = "El apellido es requerido")
    private String surname;
    @Pattern(regexp = "^[0-9]+$", message = "El telefono no debe contener caracteres especiales")
    @NotEmpty(message = "El telefono es requerido")
    private String phone;
}