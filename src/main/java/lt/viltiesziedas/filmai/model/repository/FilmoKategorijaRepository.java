package lt.viltiesziedas.filmai.model.repository;

import lt.viltiesziedas.filmai.model.entity.FilmoKategorija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmoKategorijaRepository extends JpaRepository<FilmoKategorija, Long> {
    FilmoKategorija findById(long id);

}
