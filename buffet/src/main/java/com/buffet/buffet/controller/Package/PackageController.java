package com.buffet.buffet.controller.Package;

import com.buffet.buffet.controller.Package.PackageDTO.PackageDTO;
import com.buffet.buffet.controller.Package.PackageDTO.UpdateStatusDTO;
import com.buffet.buffet.services.Package.PackageService;
import com.buffet.buffet.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/package")
@CrossOrigin(origins = {"*"})
public class PackageController {
    @Autowired
    PackageService packageService;
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<CustomResponse> register(@Valid @RequestBody PackageDTO packageDTO) {
        return packageService.registerPackage(packageDTO);
    }
    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<CustomResponse> update(@Valid @RequestBody PackageDTO packageDTO) {
        return packageService.updatePackage(packageDTO);
    }
    @PutMapping(value = "/updateStatus", produces = "application/json")
    public ResponseEntity<CustomResponse> updateStatus(@Valid @RequestBody UpdateStatusDTO updateStatus) {
        return packageService.updateStatus(updateStatus);
    }
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse> getPackages(){
        return packageService.getAll();
    }
}
