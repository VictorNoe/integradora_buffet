package com.buffet.buffet.model.useraccount;

import com.buffet.buffet.model.Status.StatusModel;
import com.buffet.buffet.model.userinfo.UserInfoModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_account")
@Entity
public class UserAccountModel {
    @Id
    @Column(name = "id_user_account", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUserAccount;
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Size(max = 500)
    private String token;
    @OneToOne(optional = false)
    @JoinColumn(name = "fk_user_info", referencedColumnName = "id_user_info")
    private UserInfoModel fkUserInfo;

    @OneToOne(optional = false, targetEntity = StatusModel.class)
    @JoinColumn(name = "fk_status", referencedColumnName = "id_status")
    private StatusModel fkStatus;

}
