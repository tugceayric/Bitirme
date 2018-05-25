package baslangic.myapp.Kaynak;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Meslek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ad;

    @OneToMany(mappedBy = "meslek")
    private List<CalisanDetay> cdetay=new ArrayList<>();

    public Meslek(String ad) {
        this.ad = ad;
    }

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public List<CalisanDetay> getCdetay() {
        return cdetay;
    }

    public Meslek()
    {

    }

}
