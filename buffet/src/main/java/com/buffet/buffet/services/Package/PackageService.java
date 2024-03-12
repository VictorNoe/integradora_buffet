package com.buffet.buffet.services.Package;

import com.buffet.buffet.controller.Package.packagedto.PackageDTO;
import com.buffet.buffet.model.Package.Package;
import com.buffet.buffet.model.Package.PackageRepository;
import com.buffet.buffet.model.updatestatus.UpdateStatus;
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
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;
@Transactional
@Service
@Slf4j
public class PackageService {
    private final PackageRepository packageRepository;
    private final CategoryRepository categoryRepository;
    private final StatusRepository statusRepository;
    @Autowired

    public PackageService(PackageRepository packageRepository, CategoryRepository categoryRepository, StatusRepository statusRepository) {
        this.packageRepository = packageRepository;
        this.categoryRepository = categoryRepository;
        this.statusRepository = statusRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> registerPackage(PackageDTO packageDTO) {
        Package exist = packageRepository.findByPackageName(packageDTO.getPackageName());
        if (exist != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "El nombre ya ha sido registrado"));
        }

        Optional<Status> status = statusRepository.findByStatusName("enable");
        if (status.isEmpty()) {
            log.error("Status inexistente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Status no valido"));
        }

        Optional<Category> category = categoryRepository.findByCategoryName(packageDTO.getCategory());
        if (category.isEmpty()) {
            log.error("Categoría inexistente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Categoría invalida"));
        }

        Package packageSave = new Package();


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CustomResponse(packageRepository.save( saveOrUpdatePackage(packageDTO, status, category, packageSave)), false, HttpStatus.CREATED.value(), "Paquete registrado"));
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> updatePackage(PackageDTO packageDTO) {
        Package packageUpdate = packageRepository.findByPackageName(packageDTO.getPackageName());
        if(packageUpdate==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "El nombre del paquete no existe"));
        }
        Optional<Status> status = statusRepository.findByStatusName("enable");
        if(status.isPresent()){
            Optional<Category> category = categoryRepository.findByCategoryName(packageDTO.getCategory());
            if (category.isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new CustomResponse(packageRepository.saveAndFlush(saveOrUpdatePackage(packageDTO, status, category, packageUpdate)), false, HttpStatus.CREATED.value(), "Paquete actualizado"));
            }else {
                log.error("Categoria inexistente");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Categoria no encontrada"));
            }
        }else {
            log.error("Status inexistente");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Status no encontrado"));
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> updateStatus(UpdateStatus updateStatus){
        Optional<Status> statusExist = statusRepository.findByStatusName(updateStatus.getStatus());
    if (statusExist.isPresent()){
        Package packageUpdate = this.packageRepository.findByPackageName(updateStatus.getName());
        if(packageUpdate==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "Paquete invalido"));
        }
        packageUpdate.setStatus(statusExist.get());
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(packageRepository.saveAndFlush(packageUpdate),false,200,"Status actualizado"));
    }else {
        log.error("Status inexistente");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Status inexistente"));
    }
    }
        @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(packageRepository.findAll(),false,200,"OK"));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> getPackage(String packageName) {
        Package packageExist = packageRepository.findByPackageName(packageName);
        if (packageExist != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(packageExist, false, 200, "OK"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(null, true, 404, "Paquete invalido"));

        }
    }
    private Package saveOrUpdatePackage(PackageDTO packageDTO, Optional<Status> status, Optional<Category> category, Package packageSave) {
        packageSave.setPackageName(packageDTO.getPackageName());
        packageSave.setPackageDescription(packageDTO.getPackageDescription());
        packageSave.setImage(packageDTO.getImage());
        packageSave.setDiscount(packageDTO.getDiscount());
        packageSave.setPrice(packageDTO.getPrice());
        packageSave.setAbility(packageDTO.getAbility());
        packageSave.setCategory(category.get());
        packageSave.setStatus(status.get());
        return packageSave;
    }
}
