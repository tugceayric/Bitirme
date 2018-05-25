package baslangic.myapp.Kaynak;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Calisan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ad;
    private String soyad;
    private String adres;
    private String sifre;
    private long tc;
    private long telefon;


    @ManyToMany
    @JoinTable(name = "calisan_uzmanlik",joinColumns =@JoinColumn(name = "calisan_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "calisanDetay_id",referencedColumnName = "id"))
    private List<CalisanDetay> detaylar=new ArrayList<>();


    @OneToMany(mappedBy = "calisan")
    private List<Randevu> randevuListe=new ArrayList<>();

    public Calisan(String ad, String soyad, String adres, String sifre, long tc, long telefon) {
        this.ad = ad;
        this.soyad = soyad;
        this.adres = adres;
        this.sifre = sifre;
        this.tc = tc;
        this.telefon = telefon;

    }

    public int getId() {return id; }

    public String getAd() {return ad; }

    public String getSoyad() {
        return soyad;
    }

    public String getAdres() {
        return adres;
    }

    public long getTc() {
        return tc;
    }

    public long getTelefon() {
        return telefon;
    }

    public String getSifre() {
        return sifre;
    }

    public List<CalisanDetay> getDetayListe() {
        return detaylar;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public void setTc(long tc) {
        this.tc = tc;
    }

    public void setTelefon(long telefon) {
        this.telefon = telefon;
    }

    public void setDetayListe(List<CalisanDetay> detayListe) {
        this.detaylar = detayListe;
    }


    public List<Randevu> getRandevuListe() {
        return randevuListe;
    }

    public Calisan(){

    }

}
