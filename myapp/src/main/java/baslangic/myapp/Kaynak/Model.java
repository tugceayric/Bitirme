package baslangic.myapp.Kaynak;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ad;

    public Model(String ad) {
        this.ad = ad;
    }

    public String getAd() {
        return ad;
    }

    public int getId() {
        return id;
    }

    public Model()
    {

    }
}
