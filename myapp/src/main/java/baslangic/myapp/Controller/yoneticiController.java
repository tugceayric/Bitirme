package baslangic.myapp.Controller;

import baslangic.myapp.Kaynak.Yonetici;
import baslangic.myapp.Repository.yoneticiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import baslangic.myapp.Repository.calisanRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value="/yonetici")
public class yoneticiController {
    yoneticiRepository yoneticiRep;

    @Autowired
    public yoneticiController(yoneticiRepository yoneticiRep) {
        this.yoneticiRep = yoneticiRep;
    }

    @RequestMapping(value="/all",method = RequestMethod.GET)
    public List<Yonetici>getAll()
    {
        return yoneticiRep.findAll();
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public List<Yonetici>create(@RequestBody Yonetici yonetici)
    {
        yoneticiRep.save(yonetici);
        return yoneticiRep.findAll();
    }


    @RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
    public List<Yonetici>remove (@PathVariable int id)
    {
        yoneticiRep.deleteById(id);
        return yoneticiRep.findAll();
    }

    @RequestMapping(value="/login/{kullaniciadi}/{sifre}",method = RequestMethod.GET)
    public ResponseEntity<?> loginn(@PathVariable String kullaniciadi, @PathVariable String sifre)
    {
        Yonetici yonetici=null;
        yonetici= yoneticiRep.findYoneticiByKullaniciadiAndSifre(kullaniciadi,sifre);
        if(yonetici!=null)
        {
            return new ResponseEntity<>(yonetici, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);

    }
}
