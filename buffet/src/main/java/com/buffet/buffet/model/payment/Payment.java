package com.buffet.buffet.model.payment;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_payment")
    private UUID idPayment;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "transaction_date")
    private Date transactionDate;
    @Column(name = "transaction_description")
    private String transactionDescription;
    @PrePersist
    private void generateUUID() {
        if (this.idPayment == null) {
            this.idPayment = UUID.randomUUID();
        }
    }
}
