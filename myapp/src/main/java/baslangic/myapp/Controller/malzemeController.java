package baslangic.myapp.Controller;

import baslangic.myapp.Kaynak.Malzeme;
import baslangic.myapp.Kaynak.MalzemeDetay;
import baslangic.myapp.Repository.kategoriRepository;
import baslangic.myapp.Repository.malzemeDetayRepository;
import baslangic.myapp.Repository.malzemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/malzemeler")
public class malzemeController {
    malzemeRepository malzemeRep;
    kategoriRepository kategoriRep;
    malzemeDetayRepository malzemeDetayRep;

    @Autowired
    public malzemeController(malzemeRepository malzemeRep, kategoriRepository kategoriRep,malzemeDetayRepository malzemeDetayRep) {
        this.malzemeRep = malzemeRep;
        this.kategoriRep = kategoriRep;
        this.malzemeDetayRep=malzemeDetayRep;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Malzeme> getAll(){
        return malzemeRep.findAll();
    }


    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public List<Malzeme> updatemalzeme(@PathVariable int id,@RequestBody Malzeme malzeme)
    {
        Malzeme guncelmalzeme=malzemeRep.findMalzemeById(id);
        if (!malzeme.getAd().equals("")){
            guncelmalzeme.setAd(malzeme.getAd());
        }
        if (!(malzeme.getMalzemeSayisi()==0)){
            guncelmalzeme.setMalzemeSayisi(malzeme.getMalzemeSayisi());
        }

        malzemeRep.save(guncelmalzeme);
        return malzemeRep.findAll();
    }


    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public List<Malzeme> remove(@PathVariable int id)
    {
        malzemeRep.deleteById(id);
        return malzemeRep.findAll();
    }

    @RequestMapping(value = "/add/{ad}/{adet}/{kategori_id}/{malzemeDetay_id}",method = RequestMethod.GET)
    public List<Malzeme>addnewmalzeme(@PathVariable String ad,@PathVariable int adet,@PathVariable int kategori_id,@PathVariable int malzemeDetay_id)
    {
        Malzeme malzeme=new Malzeme(ad,adet);
        malzeme.setKategori(kategoriRep.findKategoriById(kategori_id));
        List<MalzemeDetay> detayim=new ArrayList<>();
        detayim.add(malzemeDetayRep.findMalzemeDetayById(malzemeDetay_id));
        malzeme.setDetaylar(detayim);
        malzemeRep.save(malzeme);
        return malzemeRep.findAll();
    }
    @RequestMapping(value = "/mdetay/{malzeme_id}/{malzemeDetay_id}",method = RequestMethod.GET)
    public List<Malzeme> addcalisandetay( @PathVariable int malzeme_id,@PathVariable int malzemeDetay_id )
    {
        Malzeme malzeme=malzemeRep.findMalzemeById(malzeme_id);
        MalzemeDetay malzemeDetay=malzemeDetayRep.findMalzemeDetayById(malzemeDetay_id);
        malzeme.getDetaylar().add(malzemeDetay);
        malzemeRep.save(malzeme);
        return malzemeRep.findAll();

    }

}
