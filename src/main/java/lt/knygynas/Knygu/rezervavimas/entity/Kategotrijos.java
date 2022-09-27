package lt.knygynas.Knygu.rezervavimas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Kategotrijos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String pavadinimas;

    private String aprasymas;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "knygos_kategorijos",
            joinColumns = @JoinColumn(name = "kategorijos_id"),
            inverseJoinColumns = @JoinColumn(name = "knygos_id")
    )
    Set<Knygos> KnygaSuKategorija;
    public Kategotrijos() {
    }

    public Kategotrijos(int id, String pavadinimas, String aprasymas, Set<Knygos> knygaSuKategorija) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.aprasymas = aprasymas;
        KnygaSuKategorija = knygaSuKategorija;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Set<Knygos> getKnygaSuKategorija() {
        return KnygaSuKategorija;
    }

    public void setKnygaSuKategorija(Set<Knygos> knygaSuKategorija) {
        KnygaSuKategorija = knygaSuKategorija;
    }

    @Override
    public String toString() {
        return "Kategotrijos{" +
                "id=" + id +
                ", pavadinimas='" + pavadinimas + '\'' +
                ", aprasymas='" + aprasymas + '\'' +
                ", KnygaSuKategorija=" + KnygaSuKategorija +
                '}';
    }
}
