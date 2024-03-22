package com.buffet.buffet.controller.worker.workerdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.sql.Time;

@Data
public class WorkerDto {
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "El número de trabajador no debe contener caracteres especiales")
    @NotEmpty(message = "El numero de trabajador es requerido")
    @Length(max = 20 , message = "El numero de trabajador no debe tener más de 20 caracteres")
    private String numWorker;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "La contraseña no puede estar en blanco")
    @Length(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String workerPassword;
    @NotNull(message = "La hora de inicio es requerida")
    private Time startHour;
    private Time endHour;
    @Pattern(regexp = "^[a-zA-ZñÑ ]+$", message = "El nombre no debe contener caracteres especiales")
    @NotEmpty(message = "El nombre es requerido")
    @Length(max = 20 , message = "El nombre no debe tener más de 20 caracteres")
    private String name;
    @Pattern(regexp = "^[a-zA-ZñÑ ]+$", message = "El apellido no debe contener caracteres especiales")
    @NotEmpty(message = "El apellido es requerido")
    @Length(max = 20 , message = "El apellido no debe tener más de 20 caracteres")
    private String lastname;
    @Pattern(regexp = "^[a-zA-ZñÑ ]+$", message = "El apellido no debe contener caracteres especiales")
    @Length(max = 20 , message = "El apellido no debe tener más de 20 caracteres")
    @NotEmpty(message = "El apellido es requerido")
    private String surname;
    @Pattern(regexp = "^[0-9]+$", message = "El telefono no debe contener caracteres especiales")
    @NotEmpty(message = "El telefono es requerido")
    @Length(max = 15 , message = "El telefono no debe tener más de 15 caracteres")
    private String phone;
}
