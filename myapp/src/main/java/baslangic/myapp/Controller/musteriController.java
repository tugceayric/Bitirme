package baslangic.myapp.Controller;

import baslangic.myapp.Kaynak.Musteri;
import baslangic.myapp.Repository.musteriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/musteriler")
public class musteriController {
    musteriRepository musteriRep;

    @Autowired
    public musteriController(musteriRepository musteriRep) {
        this.musteriRep = musteriRep;
    }

    @RequestMapping(value="/all",method = RequestMethod.GET)
    public List<Musteri> getAll()
    {
        return musteriRep.findAll();
    }

    @RequestMapping(value="/update/{id}",method = RequestMethod.POST)
    public List<Musteri> updatemusteri(@PathVariable int id,@RequestBody Musteri musteri)
    {
        Musteri guncelmusteri=musteriRep.findMusteriById(id);
        guncelmusteri.setAd(musteri.getAd());
        guncelmusteri.setSoyad(musteri.getSoyad());
        guncelmusteri.setTelefon(musteri.getTelefon());
        guncelmusteri.setSifre(musteri.getSifre());
        guncelmusteri.setMail(musteri.getMail());
        musteriRep.save(guncelmusteri);
        return musteriRep.findAll();
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public List<Musteri> remove(@PathVariable int id)
    {
        musteriRep.delete(musteriRep.findMusteriById(id));
        return musteriRep.findAll();
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Musteri addnewmusteri(@RequestBody Musteri musteri)
    {
        musteriRep.save(musteri);
        return musteri;
    }

    @RequestMapping(value="/login/{mail}/{sifre}",method = RequestMethod.GET)
    public ResponseEntity<?> loginn(@PathVariable String mail, @PathVariable String sifre)
    {
        Musteri musteri=null;
        musteri= musteriRep.findMusteriByMailAndSifre(mail,sifre);
        if(musteri!=null)
        {
            return new ResponseEntity<>(musteri, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);

    }


}
