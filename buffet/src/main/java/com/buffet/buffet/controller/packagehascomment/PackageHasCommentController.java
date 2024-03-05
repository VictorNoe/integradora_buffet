package com.buffet.buffet.controller.packagehascomment;

import com.buffet.buffet.services.packagehascomment.PackageHasCommentService;
import com.buffet.buffet.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/packageHasComment")
@CrossOrigin(origins = {"*"})
public class PackageHasCommentController {
    @Autowired
    private PackageHasCommentService packageHasCommentService;

    @GetMapping(value = "/{packageName}", produces = "application/json")
    public ResponseEntity<CustomResponse> getPackageHasComment(@PathVariable String packageName) {
        return packageHasCommentService.getPackageHasComment(packageName);
    }
}
