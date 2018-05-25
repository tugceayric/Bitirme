package baslangic.myapp.Kaynak;

import org.springframework.boot.lang.UsesUnsafeJava;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Islem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ad;

    @ManyToMany(mappedBy = "islemListe")
    private List<Randevu> randevum= new ArrayList<>();

    public Islem(String ad, List<Randevu> randevum) {
        this.ad = ad;
        this.randevum = randevum;
    }

    public void setRandevum(List<Randevu> randevum) {
        this.randevum = randevum;
    }

    public Islem(String ad) {
        this.ad = ad;
    }

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public Islem() {}
}
