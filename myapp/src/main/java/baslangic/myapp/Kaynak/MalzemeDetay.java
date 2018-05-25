package baslangic.myapp.Kaynak;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MalzemeDetay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String renk;

    @ManyToMany(mappedBy = "detaylar")
    private List<Malzeme>malzemem=new ArrayList<>();

    public MalzemeDetay(String renk) {
        this.renk = renk;
    }

    public int getId() {
        return id;
    }

    @JsonIgnore
    public List<Malzeme> getMalzemem() {
        return malzemem;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }


    public String getRenk() {
        return renk;
    }

    public MalzemeDetay()
    {

    }
}
