package com.buffet.buffet.services.packagehascomment;

import com.buffet.buffet.model.Package.Package;
import com.buffet.buffet.model.Package.PackageRepository;
import com.buffet.buffet.model.package_has_comment.PackageHasCommentRepository;
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
    private final  PackageRepository packageRepository;
    @Autowired
    public PackageHasCommentService(PackageHasCommentRepository _packageHasCommentRepository, PackageRepository _packageRepository) {
        this.packageHasCommentRepository = _packageHasCommentRepository;
        this.packageRepository = _packageRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<CustomResponse> getPackageHasComment(String packageName) {
        Package packet = packageRepository.findByPackageName(packageName);
        if (packet != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CustomResponse(packageHasCommentRepository.findByPackage(packet.getId()), false, HttpStatus.OK.value(), "OK"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomResponse(null, true, HttpStatus.NOT_FOUND.value(), "Paquete no encontrado"));
        }
    }

}
