package com.buffet.buffet.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomResponse {
    Object data;
    boolean error;
    int statusCode;
    String message;
}
