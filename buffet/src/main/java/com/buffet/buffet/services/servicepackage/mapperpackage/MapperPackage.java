package com.buffet.buffet.services.servicepackage.mapperpackage;

import com.buffet.buffet.controller.servicepackage.packagedto.ImageDTO;
import com.buffet.buffet.model.packageimage.PackageImage;

import java.util.List;
import java.util.stream.Collectors;

public class MapperPackage {

    public static List<ImageDTO> mapPackageImagesToImageDTOs(List<PackageImage> packageImages) {
        return packageImages.stream()
                .map(MapperPackage::mapPackageImageToImageDTO)
                .collect(Collectors.toList());
    }

    public static ImageDTO mapPackageImageToImageDTO(PackageImage packageImage) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setImage(packageImage.getImage());
        imageDTO.setNumImage(packageImage.getNumImage());
        // Si deseas mapear más atributos, hazlo aquí
        return imageDTO;
    }
}
