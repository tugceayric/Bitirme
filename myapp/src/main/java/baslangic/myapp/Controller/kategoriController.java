package baslangic.myapp.Controller;

import baslangic.myapp.Kaynak.Kategori;
import baslangic.myapp.Kaynak.Malzeme;
import baslangic.myapp.Repository.kategoriRepository;
import baslangic.myapp.Repository.malzemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/kategoriler")
public class kategoriController {

    kategoriRepository kategoriRep;
    malzemeRepository malzemeRep;

    @Autowired
    public kategoriController(kategoriRepository kategoriRep, malzemeRepository malzemeRep) {
        this.kategoriRep = kategoriRep;
        this.malzemeRep = malzemeRep;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Kategori> getAll()
    {
        return kategoriRep.findAll();
    }

   @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public List<Kategori> updatekategori(@PathVariable int id,@RequestBody Kategori kategori)
    {
        Kategori guncelkategori=kategoriRep.findKategoriById(id);
        guncelkategori.setAd(kategori.getAd());
        kategoriRep.save(guncelkategori);
        return kategoriRep.findAll();
    }


    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public List<Kategori> remove(@PathVariable int id)
    {
        kategoriRep.deleteById(id);
        return kategoriRep.findAll();
    }


    @RequestMapping(value = "/add/{ad}",method = RequestMethod.GET)
    public List<Kategori> addnewkategori(@PathVariable String ad)
    {
        Kategori kategori=new Kategori(ad);
        kategoriRep.save(kategori);
        return kategoriRep.findAll();
    }

    @RequestMapping(value = "/kdetay/{kategori_id}/{malzeme_id}",method = RequestMethod.GET)
    public List<Kategori> addnkategoridetay(@PathVariable int kategori_id,@PathVariable int malzeme_id)
    {
        Kategori kategori=kategoriRep.findKategoriById(kategori_id);
        Malzeme malzeme=malzemeRep.findMalzemeById(malzeme_id);
        kategori.getMalzemeListe().add(malzeme);
        kategoriRep.save(kategori);
        return kategoriRep.findAll();
    }


}
