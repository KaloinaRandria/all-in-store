package mg.working.allinstore.repository;

import mg.working.allinstore.model.entity.util.Udm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UdmRepo extends JpaRepository<Udm,Integer> {
}
