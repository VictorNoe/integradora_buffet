package com.buffet.buffet.model.useraccount;

import com.buffet.buffet.model.status.Status;
import com.buffet.buffet.model.userinfo.UserInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_account")
@Entity
public class UserAccount {
    @Id
    @Column(name = "id_user_account", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID idUserAccount;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(max = 500)
    private String token;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column
    private Date login_time;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column
    private Date logout_time;
    @OneToOne(optional = false)
    @JoinColumn(name = "fk_user_info", referencedColumnName = "id_user_info")
    private UserInfo fkUserInfo;
    @OneToOne(optional = false, targetEntity = Status.class)
    @JoinColumn(name = "fk_status", referencedColumnName = "id_status")
    private Status fkStatus;
    @PrePersist
    private void generateUUID() {
        if (this.idUserAccount == null) {
            this.idUserAccount = UUID.randomUUID();
        }
    }
}
