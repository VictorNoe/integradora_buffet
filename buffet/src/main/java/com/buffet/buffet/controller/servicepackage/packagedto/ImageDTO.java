package com.buffet.buffet.controller.servicepackage.packagedto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class ImageDTO {
    private String image;
    private Integer numImage;
    private String packageName;
}
