package com.buffet.buffet.model.address;

import com.buffet.buffet.model.useraccount.UserAccount;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_address",length = 16)
    private UUID idAddress;
    @Column(name = "street",nullable = false)
    private String street;
    @Column(name = "district",nullable = false)
    private String district;
    @Column(name = "postal_code",nullable = false)
    private String postalCode;
    @Column(name = "city",nullable = false)
    private String city;
    @Column(name = "comments",nullable = false)
    private String comments;
    @Column(name = "created_at")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "fk_userAccount")
    private UserAccount userAccount;

    @PrePersist
    private void generateUUID() {
        Date currentDateAddress = new Date();
        if (this.createdAt==null){
            this.createdAt=currentDateAddress;
        }
        if (this.idAddress == null) {
            this.idAddress = UUID.randomUUID();
        }
    }
}
