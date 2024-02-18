package com.buffet.buffet.model.assigment;

import com.buffet.buffet.model.orders.Order;
import com.buffet.buffet.model.useraccount.UserAccount;
import jakarta.persistence.*;

@Entity
@Table(name = "worker_assignment")
public class WorkerAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assigment")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fk_user_account")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "fk_order")
    private Order packageOrder;

}