package com.buffet.buffet.model.category;
import com.buffet.buffet.model.servicepackage.ServicePackage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service_category")
public class Category {

    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_service_category",length = 16)
    private UUID idServiceCategory;

    @Column(name = "category_name",nullable = false)
    private String categoryName;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<ServicePackage> packages;
    @PrePersist
    private void generateUUID() {
        if (this.idServiceCategory == null) {
            this.idServiceCategory = UUID.randomUUID();
        }
    }
}


