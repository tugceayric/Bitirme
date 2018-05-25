package baslangic.myapp.Kaynak;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Malzeme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ad;
    private int malzemeSayisi;

       @ManyToOne
    @JoinColumn(name = "kategori_id")
    private Kategori kategori;

    @ManyToMany
    @JoinTable(name = "malzeme_ozellik",joinColumns = @JoinColumn(name = "malzeme_id",referencedColumnName = "id"),inverseJoinColumns=@JoinColumn(name = "malzemeDetay_id",referencedColumnName = "id"))
    private List<MalzemeDetay> detaylar=new ArrayList<>();

    public Malzeme(String ad, int malzemeSayisi) {
        this.ad = ad;
        this.malzemeSayisi = malzemeSayisi;
    }


    public Malzeme(String ad, int malzemeSayisi, Kategori kategori, List<MalzemeDetay> detaylar) {
        this.ad = ad;
        this.malzemeSayisi = malzemeSayisi;
        this.kategori = kategori;
        this.detaylar = detaylar;
    }

    public String getAd() {
        return ad;
    }

    public int getId() {
        return id;
    }

    @JsonIgnore
    public List<MalzemeDetay> getDetaylar() {
        return detaylar;
    }

    public int getMalzemeSayisi() {
        return malzemeSayisi;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setMalzemeSayisi(int malzemeSayisi) {
        this.malzemeSayisi = malzemeSayisi;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public void setDetaylar(List<MalzemeDetay> detaylar) {
        this.detaylar = detaylar;
    }

    public Malzeme()
    {

    }
}
