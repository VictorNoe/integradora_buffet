package com.buffet.buffet.model.orders;


import com.buffet.buffet.model.address.Address;
import com.buffet.buffet.model.servicepackage.ServicePackage;
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
import jakarta.persistence.OneToOne;
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
    @Column(name = "id_package_order")
    private UUID idPackageOrder;
    @Column(name = "num_order",nullable = false)
    private String numOrder;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "order_date",nullable = false)
    private Date orderDate;

    @Column(name = "order_price",nullable = false)
    private Double orderPrice;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "created_at")
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "fk_user_account")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "fk_service_package")
    private ServicePackage servicePackage;

    @ManyToOne
    @JoinColumn(name = "fk_status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "fk_payment")
    private Payment payment;
    @OneToOne(optional = false, targetEntity = Address.class)
    @JoinColumn(name = "fk_address", referencedColumnName = "id_address")
    private Address address;
    @PrePersist
    public void prePresist(){
        Date currentDateOrder= new Date();
        if (this.createdAt==null){
            this.createdAt = currentDateOrder;
        }
        if (this.idPackageOrder == null) {
            this.idPackageOrder = UUID.randomUUID();
        }
    }
}