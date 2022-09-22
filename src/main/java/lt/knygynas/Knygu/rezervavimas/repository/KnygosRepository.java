package lt.knygynas.Knygu.rezervavimas.repository;

import lt.knygynas.Knygu.rezervavimas.entity.Knygos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnygosRepository extends JpaRepository<Knygos, Integer> {

    Knygos findById(int id);

    Knygos findByPavadinimas(String pavadinimas);
}
