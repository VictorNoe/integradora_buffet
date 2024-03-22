package com.buffet.buffet.controller.worker.workerdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.sql.Time;

@Data
public class WorkerDto {
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El numero de trabajador no debe contener caracteres especiales")
    @NotEmpty(message = "El numero de trabajador es requerido")
    private String numWorker;
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
@NotEmpty(message = "La contraseña no puede estar en blanco")
@Length(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
private String workerPassword;
private Time startHour;
private Time endHour;
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
