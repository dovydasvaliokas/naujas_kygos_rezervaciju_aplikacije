package lt.knygynas.Knygu.rezervavimas.repository;

import lt.knygynas.Knygu.rezervavimas.entity.Autorius;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoriausRepository extends JpaRepository<Autorius, Integer> {
    Autorius findById(int id);
}
