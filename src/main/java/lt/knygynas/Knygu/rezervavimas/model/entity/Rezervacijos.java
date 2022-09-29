package lt.knygynas.Knygu.rezervavimas.model.entity;

import javax.persistence.*;

@Entity
public class Rezervacijos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String pavadinimas;

    private int kiekis;

    @ManyToOne
    @JoinColumn(name = "vartotojas_id")
    private Vartotojas kurisUzRezervavo;

    public Rezervacijos() {
    }

    public Rezervacijos(int id, String pavadinimas, int kiekis, Vartotojas kurisUzRezervavo) {
        this.id = id;
        this.pavadinimas = pavadinimas;
        this.kiekis = kiekis;
        this.kurisUzRezervavo = kurisUzRezervavo;
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

    public int getKiekis() {
        return kiekis;
    }

    public void setKiekis(int kiekis) {
        this.kiekis = kiekis;
    }

    public Vartotojas getKurisUzRezervavo() {
        return kurisUzRezervavo;
    }

    public void setKurisUzRezervavo(Vartotojas kurisUzRezervavo) {
        this.kurisUzRezervavo = kurisUzRezervavo;
    }

    @Override
    public String toString() {
        return "Rezervacijos{" +
                "id=" + id +
                ", pavadinimas='" + pavadinimas + '\'' +
                ", kiekis=" + kiekis +
                ", kurisUzRezervavo=" + kurisUzRezervavo +
                '}';
    }
}
