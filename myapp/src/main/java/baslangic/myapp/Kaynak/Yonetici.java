package baslangic.myapp.Kaynak;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Yonetici {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String kullaniciadi;
    private String sifre;


    public Yonetici(String kullaniciadi, String sifre) {
        this.kullaniciadi = kullaniciadi;
        this.sifre = sifre;
    }

    public Yonetici(String sifre, long telefon) {
        this.sifre = sifre;

    }

    public int getId() {
        return id;
    }

    public String getSifre() {
        return sifre;
    }

    public String getKullaniciadi() {
        return kullaniciadi;
    }

    public Yonetici()
    {

    }
}
