package lt.viltiesziedas.filmai.model.repository;

import lt.viltiesziedas.filmai.model.entity.Vartotojai;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VartotojasRepository extends JpaRepository<Vartotojai, Long> {
    Vartotojai findByUsername(String username);
}
