package com.buffet.buffet.model.category;
import com.buffet.buffet.model.Package.Package;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Service_Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Integer id;

    @Column(name = "category_name")
    private String categoryName;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Package> packages;
}
