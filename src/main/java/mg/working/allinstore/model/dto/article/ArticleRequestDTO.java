package mg.working.allinstore.model.dto.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequestDTO {
    private String designation;
    private String idFamille;
    private String idUdm;
}
