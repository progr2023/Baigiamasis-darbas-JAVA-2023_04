package lt.viltiesziedas.filmai.model.repository;

import lt.viltiesziedas.filmai.model.entity.Filmai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmoRepository extends JpaRepository<Filmai, Long> {
    List<Filmai> findByPavadinimasLike (String pavadinimas);
    Filmai findById (long id);

}
