package mg.working.allinstore.service.article;

import mg.working.allinstore.model.dto.article.ArticleRequestDTO;
import mg.working.allinstore.model.dto.article.ArticleResponseDTO;

import java.util.List;

public interface ArticleService {

    /**
     * Crée un nouvel article.
     *
     * @param requestDTO les données de l'article à créer
     * @return l'article créé sous forme de DTO
     */
    ArticleResponseDTO createArticle(ArticleRequestDTO requestDTO);

    /**
     * Récupère un article par son identifiant.
     *
     * @param id l'identifiant de l'article
     * @return l'article trouvé sous forme de DTO
     */
    ArticleResponseDTO getArticleById(String id);

    /**
     * Récupère tous les articles.
     *
     * @return la liste de tous les articles
     */
    List<ArticleResponseDTO> getAllArticles();

    /**
     * Met à jour un article existant.
     *
     * @param id         l'identifiant de l'article à mettre à jour
     * @param requestDTO les nouvelles données
     * @return l'article mis à jour sous forme de DTO
     */
    ArticleResponseDTO updateArticle(String id, ArticleRequestDTO requestDTO);

    /**
     * Supprime un article par son identifiant.
     *
     * @param id l'identifiant de l'article à supprimer
     */
    void deleteArticle(String id);
}
