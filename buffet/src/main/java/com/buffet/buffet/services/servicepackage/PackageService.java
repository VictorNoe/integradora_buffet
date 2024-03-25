package com.buffet.buffet.services.servicepackage;

import com.buffet.buffet.controller.servicepackage.packagedto.PackageDTO;
import com.buffet.buffet.model.packageimage.PackageImage;
import com.buffet.buffet.model.packageimage.PackageImageRepository;
import com.buffet.buffet.model.servicepackage.ServicePackage;
import com.buffet.buffet.model.servicepackage.ServicePackageRepository;
import com.buffet.buffet.model.updatestatus.UpdateStatus;
import com.buffet.buffet.model.category.Category;
import com.buffet.buffet.model.category.CategoryRepository;
import com.buffet.buffet.model.status.Status;
import com.buffet.buffet.model.status.StatusRepository;
import com.buffet.buffet.services.servicepackage.mapperpackage.MapperPackage;
import com.buffet.buffet.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@Slf4j
public class PackageService {
    private final ServicePackageRepository packageRepository;
    private final CategoryRepository categoryRepository;
    private final StatusRepository statusRepository;
    private final PackageImageRepository packageImageRepository;

    @Autowired

    public PackageService(ServicePackageRepository packageRepository, CategoryRepository categoryRepository,
                          StatusRepository statusRepository,PackageImageRepository packageImageRepository) {
        this.packageRepository = packageRepository;
        this.categoryRepository = categoryRepository;
        this.statusRepository = statusRepository;
        this.packageImageRepository = packageImageRepository;
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> registerPackage(PackageDTO packageDTO) {
        ServicePackage exist = packageRepository.findByPackageName(packageDTO.getPackageName());
        if (exist != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "El nombre del paquete ya ha sido registrado"));
        }

        Optional<Status> status = statusRepository.findByStatusNameAndStatusDescription("enable", "to_package");
        if (status.isEmpty()) {
            log.error("Status invalido en registrar paquete");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Status no valido en registrar paquete"));
        }

        Optional<Category> category = categoryRepository.findByCategoryName(packageDTO.getCategory());
        if (category.isEmpty()) {
            log.error("Categoría invalida en registrar paquete");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Categoría no valida en registrar paquete"));
        }
        ServicePackage packageSave = new ServicePackage();


        ServicePackage packageSaved= packageRepository.save( saveOrUpdatePackage(packageDTO, status, category, packageSave));
        List<PackageImage> imageList = Arrays.stream(packageDTO.getImages())
                .map(imageDTO -> new PackageImage(null,imageDTO.getImage(), imageDTO.getNumImage(), packageSaved))
                .toList();

        packageSaved.setPackageImages(imageList);
        this.packageImageRepository.saveAllAndFlush(imageList);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CustomResponse(packageSaved, false, HttpStatus.CREATED.value(), "Paquete registrado"));
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> updatePackage(PackageDTO packageDTO) {
        ServicePackage packageUpdate = packageRepository.findByPackageName(packageDTO.getPackageName());
        if(packageUpdate==null){
            log.error("El nombre del paquete a actualizar no existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "El nombre del paquete a actualizar es invalido"));
        }
        Optional<Status> status = statusRepository.findByStatusName("enable");
        if(status.isPresent()){
            Optional<Category> category = categoryRepository.findByCategoryName(packageDTO.getCategory());
            if (category.isPresent()){
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new CustomResponse(packageRepository.saveAndFlush(saveOrUpdatePackage(packageDTO, status, category, packageUpdate)), false, HttpStatus.CREATED.value(), "Paquete actualizado"));
            }else {
                log.error("Categoria no existe en actualizar paquete");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Categoria no valida en actualizar paquete"));
            }
        }else {
            log.error("Status no existe en actualizar paquete");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Status no valido en actualizar paquete"));
        }
    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<CustomResponse> updateStatus(UpdateStatus updateStatus){
        Optional<Status> statusExist = statusRepository.findByStatusNameAndStatusDescription(updateStatus.getStatus(),"to_package");
    if (statusExist.isPresent()){
        ServicePackage packageUpdate = this.packageRepository.findByPackageName(updateStatus.getName());
        if(packageUpdate==null){
            log.error("El paquete a cambiar el status no existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CustomResponse(null, true, HttpStatus.BAD_REQUEST.value(), "Paquete invalido para actualizar status"));
        }
        packageUpdate.setStatus(statusExist.get());
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(packageRepository.saveAndFlush(packageUpdate),false,200,"Status actualizado"));
    }else {
        log.error("Status a actualizar paquete no existe");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Status invalido para actualizar paquete"));
    }
    }
        @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> getAll(){
        List<ServicePackage> serviceList = this.packageRepository.findAll();
            List<PackageDTO> packageDTOList = new ArrayList<>();
            for (ServicePackage servicePackage : serviceList) {
                PackageDTO packageDTO = MapperPackage.createPackageDTO(servicePackage);
                packageDTOList.add(packageDTO);
            }
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(packageDTOList,false,200,"OK"));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> getPackagesAvailable() {
        Optional<Status> status = statusRepository.findByStatusNameAndStatusDescription("enable", "to_package");
        if(status.isPresent()){
            List<ServicePackage> serviceList = this.packageRepository.findByStatus(status.get());
            List<PackageDTO> packageDTOList = new ArrayList<>();
            for (ServicePackage servicePackage : serviceList) {
                PackageDTO packageDTO = MapperPackage.createPackageDTO(servicePackage);
                packageDTOList.add(packageDTO);
            }
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(packageDTOList, false, 200, "OK"));
        } else {
            log.error("No se encontró el estado 'enable' para obtener paquetes disponibles");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Estado 'enable' no encontrado para obtener paquetes disponibles"));
        }
    }

    public ResponseEntity<CustomResponse> getCountPackages(){
        return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(this.packageRepository.countServicePackageByStatus_StatusNameAndStatus_StatusDescription("enable","to_package"),false,200,"OK"));
    }
    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> getPackage(String packageName) {

        ServicePackage packageExist = packageRepository.findByPackageName(packageName);
        if (packageExist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomResponse(null, true, 404, "Paquete especifico invalido"));
        }

        PackageDTO packageDTO = new PackageDTO();
        MapperPackage.configurePackageDTO(packageDTO, packageExist);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomResponse(packageDTO, false, 200, "OK"));

    }
    private ServicePackage saveOrUpdatePackage(PackageDTO packageDTO, Optional<Status> status, Optional<Category> category, ServicePackage packageSave) {
        packageSave.setPackageName(packageDTO.getPackageName());
        packageSave.setPackageDescription(packageDTO.getPackageDescription());
        packageSave.setDiscount(packageDTO.getDiscount());
        packageSave.setPrice(packageDTO.getPrice());
        packageSave.setAbility(packageDTO.getAbility());
        packageSave.setCategory(category.get());
        packageSave.setStatus(status.get());
        return packageSave;
    }
}
