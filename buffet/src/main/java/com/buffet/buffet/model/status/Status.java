package com.buffet.buffet.model.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
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
    private String statusName;

    @Column(name = "status_description",nullable = false)
    private String statusDescription;
    @PrePersist
    private void generateUUID() {
        if (this.idStatus == null) {
            this.idStatus = UUID.randomUUID();
        }
    }
}
