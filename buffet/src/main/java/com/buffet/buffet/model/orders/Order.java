package com.buffet.buffet.model.orders;


import com.buffet.buffet.model.Package.Package;
import com.buffet.buffet.model.status.Status;
import com.buffet.buffet.model.paymentMethod.PaymentMethod;
import com.buffet.buffet.model.useraccount.UserAccount;
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
@Table(name = "package_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Integer id;
    @Column(name = "num_order")
    private Integer numOrder;
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
    @JoinColumn(name = "fk_payment_method")
    private PaymentMethod paymentMethod;
}