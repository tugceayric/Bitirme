package baslangic.myapp.Controller;

import baslangic.myapp.Kaynak.CalisanDetay;
import baslangic.myapp.Kaynak.MalzemeDetay;
import baslangic.myapp.Kaynak.Meslek;
import baslangic.myapp.Repository.calisanDetayRepository;
import baslangic.myapp.Repository.calisanRepository;
import baslangic.myapp.Repository.meslekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cdetaylar")
public class calisanDetayController {
    calisanDetayRepository calisanDetayRep;
    calisanRepository calisanRep;
    meslekRepository meslekRep;

    @Autowired
    public calisanDetayController(calisanDetayRepository calisanDetayRep, calisanRepository calisanRep, meslekRepository meslekRep) {
        this.calisanDetayRep = calisanDetayRep;
        this.calisanRep = calisanRep;
        this.meslekRep = meslekRep;
    }


    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<CalisanDetay> getAll()
    {
        return calisanDetayRep.findAll();
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public List<CalisanDetay>updatecalisandetay(@PathVariable int id,@RequestBody CalisanDetay calisanDetay)
    {
        CalisanDetay guncelcalisandetay=calisanDetayRep.findCalisanDetayById(id);
        guncelcalisandetay.setUzmanlik(calisanDetay.getUzmanlik());
        calisanDetayRep.save(guncelcalisandetay);
        return calisanDetayRep.findAll();
    }


    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public List<CalisanDetay> remove(@PathVariable int id)
    {
        calisanDetayRep.deleteById(id);
        return calisanDetayRep.findAll();
    }

    @RequestMapping(value = "/add/{uzmanlik}/{meslek_id}",method = RequestMethod.GET)
    public List<CalisanDetay> addnewcalisandetay(@PathVariable String uzmanlik,@PathVariable int meslek_id)
    {
        CalisanDetay calisandetay= new CalisanDetay(uzmanlik);
        Meslek meslek=meslekRep.findMeslekById(meslek_id);
        calisandetay.setMeslek(meslek);
        calisanDetayRep.save(calisandetay);
        return calisanDetayRep.findAll();

    }



}
