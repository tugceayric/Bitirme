package baslangic.myapp.Repository;

import baslangic.myapp.Kaynak.MalzemeDetay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface malzemeDetayRepository extends JpaRepository<MalzemeDetay,Integer> {
    MalzemeDetay findMalzemeDetayById(int id);
}
