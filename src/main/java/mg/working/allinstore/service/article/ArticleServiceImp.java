package mg.working.allinstore.service.article;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mg.working.allinstore.exception.ResourceNotFoundException;
import mg.working.allinstore.model.dto.article.ArticleRequestDTO;
import mg.working.allinstore.model.dto.article.ArticleResponseDTO;
import mg.working.allinstore.model.entity.Article;
import mg.working.allinstore.model.entity.util.Famille;
import mg.working.allinstore.model.entity.util.Udm;
import mg.working.allinstore.repository.ArticleRepo;
import mg.working.allinstore.repository.FamilleRepo;
import mg.working.allinstore.repository.UdmRepo;
import mg.working.allinstore.service.util.IdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleServiceImp implements ArticleService {

    private final ArticleRepo articleRepo;
    private final FamilleRepo familleRepo;
    private final UdmRepo udmRepo;
    private final IdGenerator idGenerator;

    // ------------------------------------------------------------------ //
    //  CREATE
    // ------------------------------------------------------------------ //

    @Override
    @Transactional
    public ArticleResponseDTO createArticle(ArticleRequestDTO requestDTO) {
        log.info("Création d'un article : designation={}", requestDTO.getDesignation());

        Famille famille = familleRepo.findById(Integer.valueOf(requestDTO.getIdFamille()))
                .orElseThrow(() -> new ResourceNotFoundException(Famille.class, requestDTO.getIdFamille()));

        Udm udm = udmRepo.findById(Integer.valueOf(requestDTO.getIdUdm()))
                .orElseThrow(() -> new ResourceNotFoundException(Udm.class, requestDTO.getIdUdm()));

        Article article = new Article();
        article.setId(idGenerator);
        article.setDesignation(requestDTO.getDesignation());
        article.setFamille(famille);
        article.setUdm(udm);

        Article saved = articleRepo.save(article);
        log.info("Article créé avec succès : id={}", saved.getId());

        return toResponseDTO(saved);
    }

    // ------------------------------------------------------------------ //
    //  READ
    // ------------------------------------------------------------------ //

    @Override
    @Transactional(readOnly = true)
    public ArticleResponseDTO getArticleById(String id) {
        log.info("Recherche de l'article : id={}", id);

        Article article = articleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Article.class, id));

        return toResponseDTO(article);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleResponseDTO> getAllArticles() {
        log.info("Récupération de tous les articles");

        return articleRepo.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ //
    //  UPDATE
    // ------------------------------------------------------------------ //

    @Override
    @Transactional
    public ArticleResponseDTO updateArticle(String id, ArticleRequestDTO requestDTO) {
        log.info("Mise à jour de l'article : id={}", id);

        Article article = articleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Article.class, id));

        if (requestDTO.getDesignation() != null) {
            article.setDesignation(requestDTO.getDesignation());
        }

        if (requestDTO.getIdFamille() != null) {
            Famille famille = familleRepo.findById(Integer.valueOf(requestDTO.getIdFamille()))
                    .orElseThrow(() -> new ResourceNotFoundException(Famille.class, requestDTO.getIdFamille()));
            article.setFamille(famille);
        }

        if (requestDTO.getIdUdm() != null) {
            Udm udm = udmRepo.findById(Integer.valueOf(requestDTO.getIdUdm()))
                    .orElseThrow(() -> new ResourceNotFoundException(Udm.class, requestDTO.getIdUdm()));
            article.setUdm(udm);
        }

        // Pas besoin d'appeler save() explicitement grâce au dirty checking de JPA
        // (l'entité est dans le contexte de persistance car la transaction est active)
        log.info("Article mis à jour avec succès : id={}", id);

        return toResponseDTO(article);
    }

    // ------------------------------------------------------------------ //
    //  DELETE
    // ------------------------------------------------------------------ //

    @Override
    @Transactional
    public void deleteArticle(String id) {
        log.info("Suppression de l'article : id={}", id);

        // findById + orElseThrow évite un double appel SQL (existsById + deleteById)
        Article article = articleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Article.class, id));

        articleRepo.delete(article);
        log.info("Article supprimé avec succès : id={}", id);
    }

    // ------------------------------------------------------------------ //
    //  MAPPER PRIVÉ
    // ------------------------------------------------------------------ //

    /**
     * Convertit une entité Article en ArticleResponseDTO.
     * Les associations Famille et Udm sont chargées en LAZY,
     * mais elles sont accessibles ici car on est dans la même transaction.
     */
    private ArticleResponseDTO toResponseDTO(Article article) {
        ArticleResponseDTO dto = new ArticleResponseDTO();
        dto.setId(article.getId());
        dto.setDesignation(article.getDesignation());

        if (article.getFamille() != null) {
            dto.setIdFamille(String.valueOf(article.getFamille().getId()));
            dto.setNomFamille(article.getFamille().getDesignation());
        }

        if (article.getUdm() != null) {
            dto.setIdUdm(String.valueOf(article.getUdm().getId()));
            dto.setLibelleUdm(article.getUdm().getDesignation());
            dto.setUdmAcronyme(article.getUdm().getAcronyme());
        }

        return dto;
    }
}