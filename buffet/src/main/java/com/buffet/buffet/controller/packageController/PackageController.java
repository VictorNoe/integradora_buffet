package com.buffet.buffet.controller.packageController;

import com.buffet.buffet.controller.packageController.PackageDTO.PackageDTO;
import com.buffet.buffet.services.Package.PackageService;
import com.buffet.buffet.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/package")
public class PackageController {
    @Autowired
    PackageService packageService;
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<CustomResponse> register(@Valid @RequestBody PackageDTO packageDTO) {
        return packageService.registerPackage(packageDTO);
    }
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse> getPackages(){
        return packageService.getAll();
    }
}
