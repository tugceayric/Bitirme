package baslangic.myapp.Kaynak;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@Entity
public class Randevu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date tarih;



    @ManyToOne
    @JoinColumn(name = "musteri_id")
    private Musteri musteri;

    @ManyToOne
    @JoinColumn(name = "calisan_id")
    private Calisan calisan;

    @ManyToMany
    @JoinTable(name = "randevu_islem",joinColumns =@JoinColumn(name = "randevu_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name = "islem_id",referencedColumnName = "id"))
    private List<Islem> islemListe=new ArrayList<>();

    public Randevu(Date tarih, Musteri musteri, Calisan calisan) {
        this.tarih = tarih;
        this.musteri = musteri;
        this.calisan = calisan;

    }

    public Randevu(Date tarih) {
        this.tarih = tarih;
    }

    public int getId() {
        return id;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public List<Islem> getIslemListe() {
        return islemListe;
    }


    public Musteri getMusteri() {
        return musteri;
    }

    @JsonIgnore
    public Calisan getCalisan() {
        return calisan;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    public void setCalisan(Calisan calisan) {
        this.calisan = calisan;
    }

    public void setIslemListe(List<Islem> islemListe) {
        this.islemListe = islemListe;
    }

    public Randevu()
    {

    }
}

