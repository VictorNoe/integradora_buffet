package com.buffet.buffet.model.assigment;

import com.buffet.buffet.model.orders.Order;
import com.buffet.buffet.model.useraccount.UserAccount;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "worker_assignment")
public class WorkerAssignment {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_assigment",length = 16)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "fk_user_account")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "fk_order")
    private Order packageOrder;
    @PrePersist
    private void generateUUID() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
}