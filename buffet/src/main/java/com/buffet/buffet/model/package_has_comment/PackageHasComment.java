package com.buffet.buffet.model.package_has_comment;

import com.buffet.buffet.model.Package.Package;
import com.buffet.buffet.model.useraccount.UserAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "package_has_comment")
public class PackageHasComment {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_package_has_comment",length = 16)
    private UUID id_package_has_comment;
    @Column(name = "comment_text",length = 50)
    private String comment;
    @Column(name = "comment_point")
    private Integer point;
    @Column(name = "comment_date")
    private Date comment_date;
    @ManyToOne
    @JoinColumn(name = "fk_user_account")
    private UserAccount userAccount;
    @ManyToOne
    @JoinColumn(name = "fk_package")
    private Package servicePackage;
    @PrePersist
    public void prePresist(){
        this.comment_date = new Date();
        if (this.id_package_has_comment == null) {
            this.id_package_has_comment = UUID.randomUUID();
        }
    }
}
