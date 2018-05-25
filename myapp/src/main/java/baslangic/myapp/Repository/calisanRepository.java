package baslangic.myapp.Repository;

import baslangic.myapp.Kaynak.Calisan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface calisanRepository extends JpaRepository<Calisan,Integer> {
    Calisan findCalisanById(int id);
    Calisan findCalisanByTcAndSifre(long tc,String sifre);
    Calisan findOneById(int id);

}
