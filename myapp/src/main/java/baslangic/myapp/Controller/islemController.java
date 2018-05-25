package baslangic.myapp.Controller;

import baslangic.myapp.Kaynak.Islem;
import baslangic.myapp.Kaynak.Randevu;
import baslangic.myapp.Repository.islemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/islemler")
public class islemController {
  islemRepository islemRep;

    @Autowired
    public islemController(islemRepository islemRep) {
        this.islemRep = islemRep;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
     public List<Islem> getAll()
     {
         return islemRep.findAll();
     }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public List<Islem> updataislem(@PathVariable int id,@RequestBody Islem islem)
    {
        Islem guncelislem=islemRep.findIslemById(id);
        guncelislem.setAd(islem.getAd());
        islemRep.save(guncelislem);
        return islemRep.findAll();
    }
    @RequestMapping(value = "/delete/{id}",method =RequestMethod.GET)
     public List<Islem>remove(@PathVariable int id)
     {
         islemRep.deleteById(id);
         return islemRep.findAll();
     }

    @RequestMapping(value = "/add/{ad}",method = RequestMethod.GET)
    public List<Islem> addnewislem(@PathVariable String ad)
    {
        Islem islem=new Islem(ad);
        islemRep.save(islem);
        return islemRep.findAll();
    }

}
