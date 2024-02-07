package com.buffet.buffet.model.usertype;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_type")
public class UserType {
    @Column(name = "id_user_type")
    private @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idUserType;
    @Column(nullable = false)
    private String userType;

}
