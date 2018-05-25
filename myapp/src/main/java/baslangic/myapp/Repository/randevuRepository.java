package baslangic.myapp.Repository;

import baslangic.myapp.Kaynak.Randevu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface randevuRepository extends JpaRepository<Randevu,Integer> {
         Randevu findRandevuById(int calisan_id);
         List<Randevu>findByCalisanId(int calisan_id);
         Randevu findRandevuByTarih(Date date);


}
