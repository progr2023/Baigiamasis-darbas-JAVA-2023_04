package lt.viltiesziedas.filmai.model.repository;

import lt.viltiesziedas.filmai.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByPavadinimas(String pavadinimas);

}
