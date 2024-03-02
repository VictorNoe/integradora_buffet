package com.buffet.buffet.model.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "status")
@Entity
public class Status {
    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "id_status", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID idStatus;

    @Column(name = "status_name",nullable = false)
    private String status;
    @PrePersist
    private void generateUUID() {
        if (this.idStatus == null) {
            this.idStatus = UUID.randomUUID();
        }
    }
}
