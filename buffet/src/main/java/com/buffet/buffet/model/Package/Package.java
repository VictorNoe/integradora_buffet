package com.buffet.buffet.model.Package;

import com.buffet.buffet.model.category.Category;
import com.buffet.buffet.model.status.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service_package")
public class Package {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_package")
    private UUID id;

    @Column(name = "package_name")
    private String packageName;

    @Column(name = "package_description")
    private String packageDescription;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private double price;

    @Column(name = "discount")
    private double discount;
    @Column(name = "ability")
    private Integer ability;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "created_at")
    private Date createdAt;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "modified_at")
    private Date modifiedAt;

    @ManyToOne
    @JoinColumn(name = "fk_category")
    private Category category;

    @OneToOne
    @JoinColumn(name = "fk_status")
    private Status status;

    @PrePersist
    public void prePresist(){
        this.createdAt = new Date();
        this.modifiedAt = new Date();
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
}