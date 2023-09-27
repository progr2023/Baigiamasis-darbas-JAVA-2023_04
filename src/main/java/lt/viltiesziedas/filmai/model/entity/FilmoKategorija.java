package lt.viltiesziedas.filmai.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class FilmoKategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String pavadinimas;

    @OneToMany (mappedBy = "kategorija")
    private Set<Filmai> filmai;

    public FilmoKategorija() {
    }

    public FilmoKategorija(long id) {
        this.id = id;
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

    public Set<Filmai> getFilmai() {
        return filmai;
    }

    public void setFilmai(Set<Filmai> filmai) {
        this.filmai = filmai;
    }

    @Override
    public String toString() {
        return "FilmoKategorija{" +
                "id=" + id +
                ", pavadinimas='" + pavadinimas + '\'' +
                '}';
    }

}
