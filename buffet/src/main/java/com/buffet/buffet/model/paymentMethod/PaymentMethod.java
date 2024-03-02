package com.buffet.buffet.model.paymentMethod;

import com.buffet.buffet.model.status.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_method")
public class PaymentMethod {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_payment_method")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_status")
    private Status status;
    @PrePersist
    private void generateUUID() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
}
