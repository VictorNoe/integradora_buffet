package com.buffet.buffet.controller.order.orderdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class OrderDTO {
    private Double orderPrice;
    @NotNull(message = "La fecha no puede estar en blanco")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;
    @NotEmpty(message = "La calle no puede estar en blanco")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^[a-zA-ZñÑ\\s]+$", message = "La calle no debe contener caracteres especiales")
    @Length(max = 30, message = "La calle no debe tener más de 30 caracteres")
    private String street;
    @NotEmpty(message = "La colonia no puede estar en blanco")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^[a-zA-Z0-9ñÑ ]+$", message = "La colonia no debe contener caracteres especiales")
    @Length(max = 30, message = "La colonia no debe tener más de 30 caracteres")
    private String disctric;
    @NotEmpty(message = "El codigo postal no puede estar en blanco")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^[0-9]+$", message = "El codigo postal no debe contener caracteres especiales")
    @Length(max = 8, message = "El codigo postal no debe tener más de 8 caracteres")
    private String postalCode;
    @NotEmpty(message = "El estado no puede estar en blanco")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^[a-zA-ZñÑ]+$", message = "El estado no debe contener caracteres especiales")
    @Length(max = 30, message = "El estado no debe tener más de 30 caracteres")
    private String city;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^[a-zA-ZñÑ\\s]+$", message = "La referencia no debe contener caracteres especiales")
    @Length(max = 100, message = "La referencia no debe tener más de 100 caracteres")
    private String comments;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String numOrder;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;
    private String userEmail;
    private String packageName;

}
