package mg.working.allinstore.repository;

import mg.working.allinstore.model.entity.util.Famille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilleRepo extends JpaRepository<Famille,Integer> {
}
