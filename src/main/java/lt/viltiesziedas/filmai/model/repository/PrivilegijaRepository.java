package lt.viltiesziedas.filmai.model.repository;

import lt.viltiesziedas.filmai.model.entity.Privilegija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegijaRepository extends JpaRepository<Privilegija, Long> {
    Privilegija findByPavadinimas(String pavadinimas);
}
