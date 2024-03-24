package com.buffet.buffet.model.worker;
import com.buffet.buffet.model.userinfo.UserInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_worker",length = 16)
    private UUID idWorker;
    @Column(name = "numWorker",nullable = false)
    private String numWorker;
    @Column(name = "locked_worker")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean lockedWorker;
    @Column(name = "worker_password",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String workerPassword;
    @Column(name = "start_hour",nullable = false)
    @JsonFormat(pattern="HH:mm:ss")
    private Time startHour;
    @Column(name = "end_hour",nullable = false)
    private Time endHour;
    @OneToOne
    @JoinColumn(name = "fk_user_info", referencedColumnName = "id_user_info")
    private UserInfo fkUserInfo;
    @PrePersist
    private void generateUUID() {
        if (this.idWorker == null) {
            this.idWorker = UUID.randomUUID();
        }
    }
}
