package com.buffet.buffet.model.servicepackage;

import com.buffet.buffet.model.category.Category;
import com.buffet.buffet.model.packageimage.PackageImage;
import com.buffet.buffet.model.status.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service_package")
public class ServicePackage {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_service_package")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID idServicePackage;

    @Column(name = "package_name",nullable = false)
    private String packageName;

    @Column(name = "package_description",nullable = false)
    private String packageDescription;

    @Column(name = "price",nullable = false)
    private double price;

    @Column(name = "discount",nullable = true)
    private double discount;
    @Column(name = "ability",nullable = false)
    private Integer ability;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "created_at")
    private Date createdAt;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "modified_at")
    private Date modifiedAt;

    @ManyToOne
    @JoinColumn(name = "fk_service_category")
    private Category category;

    @OneToOne
    @JoinColumn(name = "fk_status")
    private Status status;
    @OneToMany(mappedBy = "servicePackage")
    @JsonIgnore
    private List<PackageImage> packageImages;
    @PrePersist
    public void prePresist(){
        Date currentDateServicePackage = new Date();
        if (this.createdAt == null) {
            this.createdAt = currentDateServicePackage;
        }
        this.modifiedAt = new Date();
        if (this.idServicePackage == null) {
            this.idServicePackage = UUID.randomUUID();
        }
    }
}