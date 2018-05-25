package baslangic.myapp.Kaynak;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Musteri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ad;
    private String soyad;
    private long telefon;
    private String sifre;
    private String mail;


    @OneToMany(mappedBy = "musteri")
    private List<Randevu> randevuListe=new ArrayList<>();


    public Musteri(String ad, String soyad, long telefon, String sifre, String mail) {
        this.ad = ad;
        this.soyad = soyad;
        this.telefon = telefon;
        this.sifre = sifre;
        this.mail = mail;

    }

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public long getTelefon() {
        return telefon;
    }

    public String getSifre() {
        return sifre;
    }

    public String getMail() {
        return mail;
    }

    @JsonIgnore
    public List<Randevu> getRandevuListe() {
        return randevuListe;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public void setTelefon(long telefon) {
        this.telefon = telefon;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setRandevuListe(List<Randevu> randevuListe) {
        this.randevuListe = randevuListe;
    }

    public Musteri(){

    }
}
