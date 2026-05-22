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
@Table(name = "udm")
public class Udm {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id_udm")
    private int id;
    @Column(nullable = false, length = 50)
    private String designation;
    @Column(nullable = false, length = 10)
    private String acronyme;
}
