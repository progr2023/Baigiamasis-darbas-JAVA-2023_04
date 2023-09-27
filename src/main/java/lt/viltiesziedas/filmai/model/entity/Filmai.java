package lt.viltiesziedas.filmai.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Filmai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    long id;
    String pavadinimas;
    String aprasymas;
    double imdbReitingas;


    @ManyToOne
    @JoinColumn(name = "kategorijos_id")
    private FilmoKategorija kategorija;

    @OneToMany(mappedBy = "filmai")
    private Set<Komentarai> komentarai;

    public Filmai() {
    }

    public Filmai(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Filmai{" +
                "id=" + id +
                ", pavadinimas='" + pavadinimas + '\'' +
                ", aprasymas='" + aprasymas + '\'' +
                ", imdbReitingas=" + imdbReitingas +
                '}';
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public String getAprasymas() {
        return aprasymas;
    }

    public void setAprasymas(String aprasymas) {
        this.aprasymas = aprasymas;
    }

    public double getImdbReitingas() {
        return imdbReitingas;
    }

    public void setImdbReitingas(double imdbReitingas) {
        this.imdbReitingas = imdbReitingas;
    }

    public FilmoKategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(FilmoKategorija kategorija) {
        this.kategorija = kategorija;
    }

    public Set<Komentarai> getKomentarai() {
        return komentarai;
    }

    public void setKomentarai(Set<Komentarai> komentarai) {
        this.komentarai = komentarai;
    }
}
