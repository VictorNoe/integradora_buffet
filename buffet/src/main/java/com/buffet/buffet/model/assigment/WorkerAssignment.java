package com.buffet.buffet.model.assigment;

import com.buffet.buffet.model.orders.Order;
import com.buffet.buffet.model.useraccount.UserAccount;
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

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity@Table(name = "worker_assignment")
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