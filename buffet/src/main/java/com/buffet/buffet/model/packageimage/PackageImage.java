package com.buffet.buffet.model.packageimage;

import com.buffet.buffet.model.servicepackage.ServicePackage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "package_image")
public class PackageImage {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_package_image")
    private UUID idPackageImage;

    @Column(name = "image",length = 500)
    private String image;
    @Column(name = "num_image")
    private Integer numImage;
    @ManyToOne
    @JoinColumn(name = "fk_service_package")
    private ServicePackage servicePackage;

    @PrePersist
    private void generateUUID() {
        if (this.idPackageImage == null) {
            this.idPackageImage = UUID.randomUUID();
        }
    }
}
