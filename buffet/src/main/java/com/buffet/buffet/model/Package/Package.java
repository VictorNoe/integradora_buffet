package com.buffet.buffet.model.Package;

import com.buffet.buffet.model.category.Category;
import com.buffet.buffet.model.status.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Service_Packages")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_package")
    private Integer id;

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

    @Column(name = "created_at")
    private Date createdAt;

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
    }
}