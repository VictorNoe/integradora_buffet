package com.buffet.buffet.model.Status;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "status")
@Entity
public class StatusModel {
    @Id
    @Column(name = "id_status", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatus;

    @Column(nullable = false)
    private String status;
}
