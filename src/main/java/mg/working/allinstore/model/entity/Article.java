package mg.working.allinstore.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mg.working.allinstore.model.entity.util.Famille;
import mg.working.allinstore.model.entity.util.Udm;
import mg.working.allinstore.service.util.IdGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "article")
public class Article {
    @Id @Column(name = "id_article")
    private String id;
    private String designation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_famille",referencedColumnName = "id_famille")
    private Famille famille;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_udm",referencedColumnName = "id_udm")
    private Udm udm;

    public void setId(IdGenerator idGenerator) {
        this.id = idGenerator.generateId("ART","article_seq",4);
    }
}
