package mg.working.allinstore.model.entity.util;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "famille")
public class Famille {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_famille")
    private int id;
    @Column(nullable = false, length = 100)
    private String designation;
}
