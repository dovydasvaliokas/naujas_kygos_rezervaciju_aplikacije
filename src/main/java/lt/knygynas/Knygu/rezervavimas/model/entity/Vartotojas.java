package lt.knygynas.Knygu.rezervavimas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Vartotojas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "vartotojas_knygos",
            joinColumns = @JoinColumn(name = "vartotojas_id"),
            inverseJoinColumns = @JoinColumn(name = "knygos_id")
    )
    Set<Knygos> vartotojoKnygos;

    @JsonIgnore
    @OneToMany(mappedBy = "kurisUzRezervavo")
    Set<Rezervacijos> vartotojoRezervacijos;

    @ManyToMany
    @JoinTable(
            name = "vartotoju_megstamos_knygos",
            joinColumns = @JoinColumn(name = "vartotojo_id"),
            inverseJoinColumns = @JoinColumn(name = "knygos_id")
    )
    private Set<Knygos> megstamosKnygos;

    public Vartotojas() {
    }

    public Vartotojas(int id, String username, String password, Set<Knygos> vartotojoKnygos, Set<Rezervacijos> vartotojoRezervacijos, Set<Knygos> megstamosKnygos) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.vartotojoKnygos = vartotojoKnygos;
        this.vartotojoRezervacijos = vartotojoRezervacijos;
        this.megstamosKnygos = megstamosKnygos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Knygos> getVartotojoKnygos() {
        return vartotojoKnygos;
    }

    public void setVartotojoKnygos(Set<Knygos> vartotojoKnygos) {
        this.vartotojoKnygos = vartotojoKnygos;
    }

    public Set<Rezervacijos> getVartotojoRezervacijos() {
        return vartotojoRezervacijos;
    }

    public void setVartotojoRezervacijos(Set<Rezervacijos> vartotojoRezervacijos) {
        this.vartotojoRezervacijos = vartotojoRezervacijos;
    }

    public Set<Knygos> getMegstamosKnygos() {
        return megstamosKnygos;
    }

    public void setMegstamosKnygos(Set<Knygos> megstamosKnygos) {
        this.megstamosKnygos = megstamosKnygos;
    }

    @Override
    public String toString() {
        return "Vartotojas{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", vartotojoKnygos=" + vartotojoKnygos +
                ", vartotojoRezervacijos=" + vartotojoRezervacijos +
                ", megstamosKnygos=" + megstamosKnygos +
                '}';
    }
}
