package com.buffet.buffet.services.packagehascomment;

import com.buffet.buffet.model.servicepackage.ServicePackage;
import com.buffet.buffet.model.servicepackage.ServicePackageRepository;
import com.buffet.buffet.model.packagehascomment.PackageHasCommentRepository;
import com.buffet.buffet.utils.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Service
@Slf4j
public class PackageHasCommentService {
   private final PackageHasCommentRepository packageHasCommentRepository;
    private final ServicePackageRepository packageRepository;
    @Autowired
    public PackageHasCommentService(PackageHasCommentRepository packageHasCommentRepository, ServicePackageRepository packageRepository) {
        this.packageHasCommentRepository = packageHasCommentRepository;
        this.packageRepository = packageRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> getPackageHasComment(String packageName) {
        ServicePackage packet = packageRepository.findByPackageName(packageName);
        if (packet != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CustomResponse(packageHasCommentRepository.findByServicePackagePackageName(packageName), false, HttpStatus.OK.value(), "OK"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Paquete no encontrado"));
        }
    }

}
