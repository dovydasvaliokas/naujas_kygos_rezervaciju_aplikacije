package lt.knygynas.Knygu.rezervavimas.repository;

import lt.knygynas.Knygu.rezervavimas.entity.Vartotojas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VartotojoRepository extends JpaRepository<Vartotojas,Integer> {
    Vartotojas findById(int id);
}
