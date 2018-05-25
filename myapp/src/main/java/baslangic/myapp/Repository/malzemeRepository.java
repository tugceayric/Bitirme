package baslangic.myapp.Repository;

import baslangic.myapp.Kaynak.Malzeme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface malzemeRepository extends JpaRepository<Malzeme,Integer> {
    Malzeme findMalzemeById(int id);
}
