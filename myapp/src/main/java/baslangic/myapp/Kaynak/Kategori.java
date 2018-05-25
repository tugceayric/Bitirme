package baslangic.myapp.Kaynak;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kategori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ad;

    @OneToMany(mappedBy = "kategori")
    private List<Malzeme> malzemeListe=new ArrayList<>();

    public Kategori( String ad) {
        this.ad = ad;
    }

    public int getId() {
        return id;
    }

    @JsonIgnore
    public List<Malzeme> getMalzemeListe() {
        return malzemeListe;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setMalzemeListe(List<Malzeme> malzemeListe) {
        this.malzemeListe = malzemeListe;
    }

    public String getAd() {
        return ad;
    }

    public Kategori()
    {

    }

}
