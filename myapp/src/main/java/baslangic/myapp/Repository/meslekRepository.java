package baslangic.myapp.Repository;

import baslangic.myapp.Kaynak.Meslek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface meslekRepository extends JpaRepository<Meslek,Integer> {
    Meslek findMeslekById(int id);
}
