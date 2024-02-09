package com.buffet.buffet.controller.userAccount.userAccountDTO;

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
    @NotEmpty(message = "El nombre es requerido")
    private String name;
    @NotEmpty(message = "El apellido es requerido")
    private String lastname;

}