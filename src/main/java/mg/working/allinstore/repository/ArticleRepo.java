package mg.working.allinstore.repository;

import mg.working.allinstore.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends JpaRepository<Article,String> {
}
