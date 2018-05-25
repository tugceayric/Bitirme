package baslangic.myapp.Controller;

import baslangic.myapp.Kaynak.Malzeme;
import baslangic.myapp.Kaynak.MalzemeDetay;
import baslangic.myapp.Repository.malzemeDetayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/mdetaylar")
public class malzemeDetayController {
    malzemeDetayRepository malzemeDetayRep;

    @Autowired
    public malzemeDetayController(malzemeDetayRepository malzemeDetayRep) {
        this.malzemeDetayRep = malzemeDetayRep;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<MalzemeDetay> getAll()
    {
        return malzemeDetayRep.findAll();
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public List<MalzemeDetay> updatemalzeme(@PathVariable int id, @RequestBody MalzemeDetay malzemeDetay)
    {
        MalzemeDetay guncelmalzemedetay=malzemeDetayRep.findMalzemeDetayById(id);
        guncelmalzemedetay.setRenk(malzemeDetay.getRenk());
        malzemeDetayRep.save(guncelmalzemedetay);
        return malzemeDetayRep.findAll();
    }
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public List<MalzemeDetay>remove(@PathVariable int id)
    {
        malzemeDetayRep.deleteById(id);
        return  malzemeDetayRep.findAll();
    }

    @RequestMapping(value = "/add/{renk}",method = RequestMethod.GET)
    public List<MalzemeDetay> addnewmalzemedetay(@PathVariable String renk)
    {
        MalzemeDetay malzemedetay=new MalzemeDetay(renk);
        malzemeDetayRep.save(malzemedetay);
        return malzemeDetayRep.findAll();

    }
}
