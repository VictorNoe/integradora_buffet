package com.buffet.buffet.controller.packagehascomment;

import com.buffet.buffet.services.packagehascomment.PackageHasCommentService;
import com.buffet.buffet.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/packageHasComment")
@CrossOrigin(origins = {"*"})
public class PackageHasCommentController {
    private final PackageHasCommentService packageHasCommentService;
    @Autowired

    private PackageHasCommentController(PackageHasCommentService packageHasCommentService) {
        this.packageHasCommentService = packageHasCommentService;
    }

    @GetMapping(value = "/{packageName}", produces = "application/json")
    public ResponseEntity<CustomResponse> getPackageHasComment(@PathVariable String packageName) {
        return packageHasCommentService.getPackageHasComment(packageName);
    }
}
