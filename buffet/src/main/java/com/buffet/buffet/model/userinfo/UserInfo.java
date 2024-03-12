package com.buffet.buffet.model.userinfo;

import com.buffet.buffet.model.usertype.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Column(name = "id_user_info")
    @Id
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @GeneratedValue(generator = "UUID")
    private UUID idUserInfo;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String phone;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(nullable = false)
    private Date createdAt;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(nullable = true)
    private Date modifiedAt;
    @ManyToOne(optional = false, targetEntity = UserType.class)
    @JoinColumn(nullable = false, name = "fk_user_type", referencedColumnName = "id_user_type")
    private UserType fkUserType;
    @PrePersist
    public void prePresist(){
        this.createdAt = new Date();
        this.modifiedAt = new Date();
        if (this.idUserInfo == null) {
            this.idUserInfo = UUID.randomUUID();
        }
    }

}
