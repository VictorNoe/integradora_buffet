package com.buffet.buffet.model.orders;


import com.buffet.buffet.model.Package.Package;
import com.buffet.buffet.model.status.Status;
import com.buffet.buffet.model.payment.Payment;
import com.buffet.buffet.model.useraccount.UserAccount;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "package_order")
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_order")
    private UUID id;
    @Column(name = "num_order")
    private String numOrder;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "order_price")
    private Double orderPrice;
    @Column(name = "street")
    private String street;
    @Column(name = "distric")
    private String disctric;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "city")
    private String city;
    @Column(name = "comments")
    private String comments;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "created_at")
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "fk_user_account")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "fk_package")
    private Package servicePackage;

    @ManyToOne
    @JoinColumn(name = "fk_status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "fk_payment")
    private Payment paymentMethod;
    @PrePersist
    public void prePresist(){
        this.createdAt = new Date();
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
}