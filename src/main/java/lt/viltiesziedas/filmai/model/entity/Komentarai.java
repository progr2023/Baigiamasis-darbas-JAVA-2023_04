package lt.viltiesziedas.filmai.model.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
public class Komentarai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 512)
    private String tekstas;

    private Date pridejimoData;


    @ManyToOne
    @JoinColumn(name = "filmo_id")
    private Filmai filmai;

    @ManyToOne
    @JoinColumn(name = "vartotojo_id")
    private Vartotojai vartotojai;

    public Komentarai() {
    }

    public Komentarai(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTekstas() {
        return tekstas;
    }

    public void setTekstas(String tekstas) {
        this.tekstas = tekstas;
    }

    public Date getPridejimoData() {
        return pridejimoData;
    }

    public void setPridejimoData(Date pridejimoData) {
        this.pridejimoData = pridejimoData;
    }

    public Filmai getFilmai() {
        return filmai;
    }

    public void setFilmai(Filmai filmai) {
        this.filmai = filmai;
    }

    public Vartotojai getVartotojai() {
        return vartotojai;
    }

    public void setVartotojai(Vartotojai vartotojai) {
        this.vartotojai = vartotojai;
    }

    @Override
    public String toString() {
        return "Komentarai{" +
                "id=" + id +
                ", tekstas='" + tekstas + '\'' +
                ", pridejimoData=" + pridejimoData +
                '}';
    }
}
