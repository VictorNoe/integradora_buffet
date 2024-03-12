package com.buffet.buffet.model.packagehascomment;

import com.buffet.buffet.model.Package.Package;
import com.buffet.buffet.model.useraccount.UserAccount;
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
@Table(name = "package_has_comment")
public class PackageHasComment {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id_package_has_comment",length = 16)
    private UUID idPackageHasComment;
    @Column(name = "comment_text",length = 150)
    private String comment;
    @Column(name = "comment_point")
    private Integer point;
    @Column(name = "comment_date")
    private Date commentDate;
    @ManyToOne
    @JoinColumn(name = "fk_user_account")
    private UserAccount userAccount;
    @ManyToOne
    @JoinColumn(name = "fk_package")
    private Package servicePackage;
    @PrePersist
    public void prePresist(){
        this.commentDate = new Date();
        if (this.idPackageHasComment == null) {
            this.idPackageHasComment = UUID.randomUUID();
        }
    }
}
