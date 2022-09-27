package lt.knygynas.Knygu.rezervavimas.repository;

import lt.knygynas.Knygu.rezervavimas.entity.Kategotrijos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategorijosRepository extends JpaRepository<Kategotrijos,Integer> {

    Kategotrijos findByPavadinimas(String pavadinimas);
    Kategotrijos findById(int id);
}
