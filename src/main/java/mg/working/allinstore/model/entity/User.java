package mg.working.allinstore.model.entity;

import jakarta.persistence.*;
import lombok.*;
import mg.working.allinstore.model.entity.util.Role;
import mg.working.allinstore.service.util.IdGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id_user")
    private String id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String nom;

    private String prenom;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public void setId(IdGenerator idGenerator) {
        this.id = idGenerator.generateId("USR", "user_seq", 3);
    }
}