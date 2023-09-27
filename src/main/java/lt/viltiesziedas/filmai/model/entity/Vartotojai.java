package lt.viltiesziedas.filmai.model.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Vartotojai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String vardas;
    private String pavarde;
    private boolean enabled;
    private boolean expiredToken;

    @OneToMany(mappedBy = "vartotojai")
    private Set<Komentarai> komentarai;

    @ManyToMany
    @JoinTable (
            name = "vartotoju_roles",
            joinColumns = @JoinColumn (name = "vartotojo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn (name = "roles_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    public Vartotojai() {
    }

    public Vartotojai(long id, String username, String password, String vardas, String pavarde, boolean enabled, boolean expiredToken, Set<Komentarai> komentarai, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.enabled = enabled;
        this.expiredToken = expiredToken;
        this.komentarai = komentarai;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Komentarai> getKomentarai() {
        return komentarai;
    }

    public void setKomentarai(Set<Komentarai> komentarai) {
        this.komentarai = komentarai;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getPavarde() {
        return pavarde;
    }

    public void setPavarde(String pavarde) {
        this.pavarde = pavarde;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isExpiredToken() {
        return expiredToken;
    }

    public void setExpiredToken(boolean expiredToken) {
        this.expiredToken = expiredToken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Vartotojai{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", vardas='" + vardas + '\'' +
                ", pavarde='" + pavarde + '\'' +
                ", enabled=" + enabled +
                ", expiredToken=" + expiredToken +
                '}';
    }
}
