package baslangic.myapp.Repository;

import baslangic.myapp.Kaynak.Musteri;
import baslangic.myapp.Kaynak.Yonetici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface yoneticiRepository extends JpaRepository<Yonetici,Integer> {
    Yonetici findYoneticiById(int id);
    Yonetici findYoneticiByKullaniciadiAndSifre(String kullaniciadi,String sifre);
}
