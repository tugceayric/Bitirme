package baslangic.myapp.Repository;

import baslangic.myapp.Kaynak.CalisanDetay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface calisanDetayRepository extends JpaRepository<CalisanDetay,Integer> {
    CalisanDetay findCalisanDetayById(int id);
}
