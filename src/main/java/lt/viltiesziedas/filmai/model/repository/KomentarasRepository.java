package lt.viltiesziedas.filmai.model.repository;

import lt.viltiesziedas.filmai.model.entity.Komentarai;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KomentarasRepository extends JpaRepository<Komentarai, Long> {
}
