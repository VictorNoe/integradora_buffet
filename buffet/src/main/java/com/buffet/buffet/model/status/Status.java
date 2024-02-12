package com.buffet.buffet.model.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "status")
@Entity
public class Status {
    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "id_status", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idStatus;

    @Column(nullable = false)
    private String status;
}
