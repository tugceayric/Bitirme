package baslangic.myapp.Repository;

import baslangic.myapp.Kaynak.Islem;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface islemRepository extends JpaRepository<Islem,Integer> {
    Islem getIslemById(int id);
    Islem findIslemByAd (String islem_ad);
    Islem findIslemById(int id);






}
