package baslangic.myapp.Repository;

import baslangic.myapp.Kaynak.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface modelRepository extends JpaRepository<Model,Integer> {
    Model findModelById(int id);
}
