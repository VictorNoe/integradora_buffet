package com.buffet.buffet.services.servicepackage.mapperpackage;

import com.buffet.buffet.controller.servicepackage.packagedto.ImageDTO;
import com.buffet.buffet.controller.servicepackage.packagedto.PackageDTO;
import com.buffet.buffet.model.packageimage.PackageImage;
import com.buffet.buffet.model.packageimage.PackageImageRepository;
import com.buffet.buffet.model.servicepackage.ServicePackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class MapperPackage {
    private static PackageImageRepository packageImageRepository;
    @Autowired
    public MapperPackage(PackageImageRepository packageImageRepository) {
        MapperPackage.packageImageRepository = packageImageRepository;
    }

    public static List<ImageDTO> mapPackageImagesToImageDTOs(List<PackageImage> packageImages) {
        return packageImages.stream()
                .map(MapperPackage::mapPackageImageToImageDTO)
                .collect(Collectors.toList());
    }

    public static ImageDTO mapPackageImageToImageDTO(PackageImage packageImage) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setImage(packageImage.getImage());
        imageDTO.setNumImage(packageImage.getNumImage());
        return imageDTO;
    }
    public static PackageDTO createPackageDTO(ServicePackage servicePackage) {
        PackageDTO packageDTO = new PackageDTO();
        configurePackageDTO(packageDTO, servicePackage);
        return packageDTO;
    }

    public static void configurePackageDTO(PackageDTO packageDTO, ServicePackage servicePackage) {
        packageDTO.setPackageName(servicePackage.getPackageName());
        packageDTO.setCategory(servicePackage.getCategory().getCategoryName());
        packageDTO.setPackageDescription(servicePackage.getPackageDescription());
        packageDTO.setAbility(servicePackage.getAbility());
        packageDTO.setPrice(servicePackage.getPrice());
        packageDTO.setDiscount(servicePackage.getDiscount());

        List<PackageImage> imageList = packageImageRepository.findAllByServicePackage_PackageName(servicePackage.getPackageName());
        List<ImageDTO> imageDTOList = MapperPackage.mapPackageImagesToImageDTOs(imageList);
        packageDTO.setImages(imageDTOList.toArray(new ImageDTO[0]));
    }

}
