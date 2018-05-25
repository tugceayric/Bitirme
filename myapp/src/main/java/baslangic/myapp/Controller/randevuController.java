package baslangic.myapp.Controller;

import baslangic.myapp.Kaynak.Islem;
import baslangic.myapp.Kaynak.Randevu;
import baslangic.myapp.Repository.calisanRepository;
import baslangic.myapp.Repository.islemRepository;
import baslangic.myapp.Repository.musteriRepository;
import baslangic.myapp.Repository.randevuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping(value="/randevular")
public class randevuController {
    randevuRepository randevuRep;
    islemRepository islemRep;
    musteriRepository musteriRep;
    calisanRepository calisanRep;


    @Autowired
    public randevuController(randevuRepository randevuRep, islemRepository islemRep, musteriRepository musteriRep, calisanRepository calisanRep ) {
        this.randevuRep = randevuRep;
        this.islemRep = islemRep;
        this.musteriRep = musteriRep;
        this.calisanRep=calisanRep;


    }

    @RequestMapping(value="/all",method = RequestMethod.GET)
    public List<Randevu> getAll()
    {
        return randevuRep.findAll();
    }


    @RequestMapping(value = "/delete/{randevu_id}",method = RequestMethod.GET)
    public List<Randevu> remove(@PathVariable int randevu_id)
    {
        randevuRep.deleteById(randevu_id);
         return randevuRep.findAll();
    }


    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public List<Randevu> updaterandevu(@PathVariable int id,@RequestBody Randevu randevu)
    {
        Randevu guncelrandevu= randevuRep.findRandevuById(id);
        guncelrandevu.setTarih(randevu.getTarih());
        guncelrandevu.setMusteri(randevu.getMusteri());
        guncelrandevu.setCalisan(randevu.getCalisan());
        guncelrandevu.setIslemListe(randevu.getIslemListe());
         randevuRep.save(guncelrandevu);
         return randevuRep.findAll();
    }


    @RequestMapping(value = "/add/{tarih}/{musteri_id}/{calisan_id}/{gelenislemler}",method = RequestMethod.GET)
    public List<Randevu> addnewrandevu(@PathVariable ("tarih") @DateTimeFormat(pattern="dd-MM-yyyy-HH:mm") Date  tarih,
                                       @PathVariable int musteri_id,
                                       @PathVariable int calisan_id,
                                       @PathVariable List<Integer> gelenislemler )
    {

        Randevu randevu= new Randevu(tarih);
        randevu.setMusteri(musteriRep.findMusteriById(musteri_id));
        randevu.setCalisan(calisanRep.findCalisanById(calisan_id));
        List<Islem>islemler=new ArrayList<>();
        for(int i=0;i<gelenislemler.size();i++){
            islemler.add(islemRep.findIslemById(gelenislemler.get(i)));
        }
        randevu.setIslemListe(islemler);
        randevuRep.save(randevu);
        return randevuRep.findAll();

    }

    @RequestMapping(value = "/rislem/{randevu_id}/{islem_id}",method = RequestMethod.GET)
    public List<Randevu> addrandevuislem( @PathVariable int randevu_id,@PathVariable int islem_id )
    {

        Randevu randevu= randevuRep.findRandevuById(randevu_id);
        Islem islem=islemRep.findIslemById(islem_id);
        randevu.getIslemListe().add(islem);
        randevuRep.save(randevu);
        return randevuRep.findAll();
    }

    @RequestMapping(value = "/search/{tarih}",method = RequestMethod.GET)
    public ResponseEntity<?> search(@PathVariable ("tarih") @DateTimeFormat(pattern="dd-MM-yyyy-HH:mm") Date  tarih){
        Randevu randevu=null;

        randevu=randevuRep.findRandevuByTarih(tarih);

        if(randevu!=null)
            return new ResponseEntity<>(randevu, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/cek/{calisan_id}",method = RequestMethod.GET)
    public List<Randevu> cek( @PathVariable int calisan_id )

    {
        List<Randevu>randevular=randevuRep.findByCalisanId(calisan_id);
        return randevular;
    }
}
