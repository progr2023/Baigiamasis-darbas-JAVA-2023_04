package lt.viltiesziedas.filmai.model.entity;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Privilegija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String pavadinimas;

    @ManyToMany(mappedBy = "privilegijos")
    private Set<Role> roles;

    public Privilegija() {
    }

    public Privilegija(long id, String pavadinimas, Set<Role> roles) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Privilegija{" +
                "id=" + id +
                ", pavadinimas='" + pavadinimas + '\'' +
                '}';
    }
}
