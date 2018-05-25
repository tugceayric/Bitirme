package baslangic.myapp.Kaynak;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CalisanDetay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uzmanlik;

    @ManyToOne
    @JoinColumn(name = "meslek_id")
    private Meslek meslek;

    @ManyToMany(mappedBy = "detaylar")
    private List<Calisan> detaylar= new ArrayList<>();


    public CalisanDetay(String uzmanlik) {
        this.uzmanlik = uzmanlik;
    }

    public int getId() {
        return id;
    }

    public void setMeslek(Meslek meslek) {
        this.meslek = meslek;
    }

    @JsonIgnore
    public Meslek getMeslek() {
        return meslek;
    }

    public String getUzmanlik() {
        return uzmanlik;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setUzmanlik(String uzmanlik) {
        this.uzmanlik = uzmanlik;
    }

    public CalisanDetay(){}
}
