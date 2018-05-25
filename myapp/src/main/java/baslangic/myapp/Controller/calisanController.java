package baslangic.myapp.Controller;

import baslangic.myapp.Kaynak.Calisan;
import baslangic.myapp.Kaynak.CalisanDetay;
import baslangic.myapp.Repository.calisanDetayRepository;
import baslangic.myapp.Repository.calisanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/calisanlar")
public class calisanController {
    calisanRepository calisanRep;
    calisanDetayRepository calisanDetayRep;
    //private List<Calisan> calisanlar;

    @Autowired
    public calisanController(calisanRepository calisanRep, calisanDetayRepository calisanDetayRep) {
        this.calisanRep = calisanRep;
        this.calisanDetayRep = calisanDetayRep;
        // calisanlar =new ArrayList<>();
        //calisanlar.add(new Calisan (1, "Tuğçe" ,"Ayrıç","ankara", "a123",12345102678L, 10L));
        //calisanlar.add(new Calisan(2,  "Fatma" ,"Sarıkaya","kayseri","b456",12302730345L, 15L));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Calisan> getAll() {
        return calisanRep.findAll();
    }

     @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public List<Calisan> updatecalisan(@PathVariable int id,@RequestBody Calisan calisan) {
        Calisan guncelcalisan=calisanRep.findCalisanById(id);

         if (!calisan.getAd().equals("")){
             guncelcalisan.setAd(calisan.getAd());
         }
         if (!calisan.getSoyad().equals("")){
             guncelcalisan.setSoyad(calisan.getSoyad());
         }
         if (!calisan.getAdres().equals("")){
             guncelcalisan.setAdres(calisan.getAdres());
         }
         if (!calisan.getSifre().equals("")){
             guncelcalisan.setSifre(calisan.getSifre());
         }

         if (!(calisan.getTc()==0)){
             guncelcalisan.setTc(calisan.getTc());
         }

         if (!(calisan.getTelefon()==0)){
             guncelcalisan.setTelefon(calisan.getTelefon());
         }

         calisanRep.save(guncelcalisan);

        return calisanRep.findAll();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public List<Calisan> remove(@PathVariable int id) {
        calisanRep.delete(calisanRep.findCalisanById(id));
        return calisanRep.findAll();

    }

    @RequestMapping(value = "/add/{ad}/{soyad}/{adres}/{sifre}/{tc}/{telefon}/{uzmanliklar}", method = RequestMethod.GET)
    public Calisan addnewcalisan(@PathVariable String ad, @PathVariable String soyad, @PathVariable String adres, @PathVariable String sifre, @PathVariable long tc, @PathVariable long telefon, @PathVariable List<Integer> uzmanliklar) {
        Calisan calisan = new Calisan(ad, soyad, adres, sifre, tc, telefon);
        List<CalisanDetay> detayim = new ArrayList<>();

        for(int i=0;i<uzmanliklar.size();i++){
            detayim.add(calisanDetayRep.findCalisanDetayById(uzmanliklar.get(i)));
        }
        calisan.setDetayListe(detayim);
        calisanRep.save(calisan);
        return calisan;

    }
    @RequestMapping(value = "/cdetay/{calisan_id}/{uzmanliklar}",method = RequestMethod.GET)
    public List<Calisan> addcalisandetay( @PathVariable int calisan_id,@PathVariable int uzmanlik_id )
    {

        Calisan calisan= calisanRep.findCalisanById(calisan_id);
        CalisanDetay uzmanlik=calisanDetayRep.findCalisanDetayById(uzmanlik_id);
        calisan.getDetayListe().add(uzmanlik);
        calisanRep.save(calisan);
        return calisanRep.findAll();

    }
    @RequestMapping(value="/login/{tc}/{sifre}",method = RequestMethod.GET)
    public ResponseEntity<?> loginn(@PathVariable Long tc, @PathVariable String sifre)
    {
        Calisan calisan=null;
        calisan= calisanRep.findCalisanByTcAndSifre(tc,sifre);
        if(calisan!=null)
        {
            return new ResponseEntity<>(calisan, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);

    }
    @RequestMapping(value = "/cek/{calisan_id}",method = RequestMethod.GET)
    public Calisan cek( @PathVariable int calisan_id )

    {
        Calisan calisan=calisanRep.findOneById(calisan_id);
        return calisan;
    }
}