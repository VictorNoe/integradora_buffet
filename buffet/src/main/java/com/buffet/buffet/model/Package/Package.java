package com.buffet.buffet.model.Package;

import com.buffet.buffet.model.category.Category;
import com.buffet.buffet.model.status.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service_package")
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
    }
}