package baslangic.myapp.Repository;

import baslangic.myapp.Kaynak.Musteri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface musteriRepository extends JpaRepository<Musteri,Integer> {
    Musteri findMusteriById(int id);
    Musteri findMusteriByMailAndSifre(String mail,String sifre);
}
