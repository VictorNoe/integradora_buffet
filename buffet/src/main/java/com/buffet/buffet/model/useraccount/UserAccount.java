package com.buffet.buffet.model.useraccount;

import com.buffet.buffet.model.address.Address;
import com.buffet.buffet.model.status.Status;
import com.buffet.buffet.model.userinfo.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
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
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "user_password",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPassword;
    @Column(name = "token")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(max = 500)
    private String token;
    @Column(name = "locked_user")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean locked;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "login_time")
    private Date loginTime;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "logout_time")
    private Date logoutTime;
    @OneToOne(optional = false)
    @JoinColumn(name = "fk_user_info", referencedColumnName = "id_user_info")
    private UserInfo fkUserInfo;
    @OneToOne(optional = false, targetEntity = Status.class)
    @JoinColumn(name = "fk_status", referencedColumnName = "id_status")
    private Status fkStatus;
    @OneToMany(mappedBy = "userAccount")
    @JsonIgnore
    private List<Address> addressList;
    @PrePersist
    private void generateUUID() {
        if (this.idUserAccount == null) {
            this.idUserAccount = UUID.randomUUID();
        }
    }
}
