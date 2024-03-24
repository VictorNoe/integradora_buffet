package com.buffet.buffet.model.assigment;

import com.buffet.buffet.model.orders.Order;
import com.buffet.buffet.model.worker.Worker;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Entity@Table(name = "worker_assignment")
public class WorkerAssignment {

    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "id_worker_assignment",nullable = false)
    private UUID idWorkerAssignment;
    @Column(name = "assignment_date",nullable = false)
    private Date assignmentDate;
    @Column(name = "created_at",nullable = false)
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "fk_worker")
    private Worker worker;

    @ManyToOne
    @JoinColumn(name = "fk_package_order")
    private Order packageOrder;
    @PrePersist
    private void prePresist() {
        if (this.idWorkerAssignment == null) {
            this.idWorkerAssignment = UUID.randomUUID();
        }
        Date currentDateAssignment = new Date();
        if (this.createdAt==null){
            this.createdAt=currentDateAssignment;
        }
    }
}