package com.buffet.buffet.controller.order.orderdto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "La calle no debe contener caracteres especiales")
    private String street;
    @NotEmpty(message = "La colonia no puede estar en blanco")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "La colonia no debe contener caracteres especiales")
    private String disctric;
    @NotEmpty(message = "El codigo postal no puede estar en blanco")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^[0-9]+$", message = "El codigo postal no debe contener caracteres especiales")
    private String postalCode;
    @NotEmpty(message = "El estado no puede estar en blanco")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El estado no debe contener caracteres especiales")
    private String city;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "La referencia no debe contener caracteres especiales")
    private String comments;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String numOrder;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;
    private Date orderDate;
    private String userEmail;
    private String packageName;

}
