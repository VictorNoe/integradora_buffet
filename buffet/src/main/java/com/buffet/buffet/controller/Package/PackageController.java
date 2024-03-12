package com.buffet.buffet.controller.Package;

import com.buffet.buffet.controller.Package.packagedto.PackageDTO;
import com.buffet.buffet.model.updatestatus.UpdateStatus;
import com.buffet.buffet.services.Package.PackageService;
import com.buffet.buffet.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/package")
@CrossOrigin(origins = {"http://localhost:5173/"})
public class PackageController {
    private final PackageService packageService;
    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<CustomResponse> register(@Valid @RequestBody PackageDTO packageDTO) {
        return packageService.registerPackage(packageDTO);
    }
    @PutMapping(value = "/update", produces = "application/json")
    public ResponseEntity<CustomResponse> update(@Valid @RequestBody PackageDTO packageDTO) {
        return packageService.updatePackage(packageDTO);
    }
    @PutMapping(value = "/updateStatus", produces = "application/json")
    public ResponseEntity<CustomResponse> updateStatus(@Valid @RequestBody UpdateStatus updateStatus) {
        return packageService.updateStatus(updateStatus);
    }
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse> getPackages(){
        return packageService.getAll();
    }
    @GetMapping(value = "/{packageName}", produces = "application/json")
    public ResponseEntity<CustomResponse> getPackage(@PathVariable String packageName){
        return packageService.getPackage(packageName);
    }
}
