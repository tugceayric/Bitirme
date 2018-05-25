package baslangic.myapp.Controller;

import baslangic.myapp.Kaynak.Calisan;
import baslangic.myapp.Kaynak.Kategori;
import baslangic.myapp.Kaynak.Malzeme;
import baslangic.myapp.Kaynak.Meslek;
import baslangic.myapp.Repository.meslekRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/meslekler")
public class meslekController  {

    meslekRepository meslekRep;

    public meslekController(meslekRepository meslekRep) {
        this.meslekRep = meslekRep;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Meslek> getAll() {
        return meslekRep.findAll();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public List<Meslek> remove(@PathVariable int id) {
        meslekRep.delete(meslekRep.findMeslekById(id));
        return meslekRep.findAll();
    }

    @RequestMapping(value = "/add/{ad}",method = RequestMethod.GET)
    public List<Meslek> addnewmeslek(@PathVariable String ad)
    {
        Meslek meslek=new Meslek(ad);
        meslekRep.save(meslek);
        return meslekRep.findAll();
    }
}

