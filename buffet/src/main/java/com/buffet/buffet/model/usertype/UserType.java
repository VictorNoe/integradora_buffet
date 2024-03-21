package com.buffet.buffet.model.usertype;
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
@Entity
@Table(name = "user_type")
public class UserType {
    @Column(name = "id_user_type")
     @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @GeneratedValue(generator = "UUID")
    private UUID idUserType;
    @Column(name = "type_name",nullable = false)
    private String typeName;
    @PrePersist
    private void generateUUID() {
        if (this.idUserType == null) {
            this.idUserType = UUID.randomUUID();
        }
    }
}
