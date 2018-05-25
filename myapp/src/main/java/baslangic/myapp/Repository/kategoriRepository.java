package baslangic.myapp.Repository;

import baslangic.myapp.Kaynak.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface kategoriRepository extends JpaRepository<Kategori,Integer> {
    Kategori findKategoriById(int id);
}
