package com.buffet.buffet.services.Package;

import com.buffet.buffet.controller.packageController.PackageDTO.PackageDTO;
import com.buffet.buffet.model.Package.Package;
import com.buffet.buffet.model.Package.PackageRepository;
import com.buffet.buffet.model.category.Category;
import com.buffet.buffet.model.category.CategoryRepository;
import com.buffet.buffet.model.status.Status;
import com.buffet.buffet.model.status.StatusRepository;
import com.buffet.buffet.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PackageService {
    @Autowired
    PackageRepository packageRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private StatusRepository statusRepository;
    public ResponseEntity<CustomResponse> registerPackage(PackageDTO packageDTO){
        Package packageSave = new Package();
        Optional<Package> exist = packageRepository.findByPackageName(packageDTO.getPackageName());
        if(exist.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "El nombre ya ah sido registrado"));
        }
        Optional<Status> status = statusRepository.findByStatus("enable");
        if(status.isPresent()){
            Optional<Category> category = categoryRepository.findByCategoryName(packageDTO.getCategory().getCategoryName());
            if (category.isPresent()){
                packageSave.setPackageName(packageDTO.getPackageName());
                packageSave.setPackageDescription(packageDTO.getPackageDescription());
                packageSave.setImage(packageDTO.getImage());
                packageSave.setDiscount(packageDTO.getDiscount());
                packageSave.setPrice(packageDTO.getPrice());
                packageSave.setCategory(category.get());
                packageSave.setStatus(status.get());
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new CustomResponse(packageRepository.save(packageSave), false, HttpStatus.CREATED.value(), "Paquete registrado"));
            }else {
                log.error("Categoria inexistente");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Categoria no encontrad"));
            }
        }else {
            log.error("Status inexistente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Status no encontrado"));
        }

    }
    public ResponseEntity<CustomResponse> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(packageRepository.findAll(),false,200,"OK"));
    }
}
