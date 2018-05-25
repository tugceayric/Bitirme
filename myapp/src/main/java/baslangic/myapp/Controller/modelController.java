package baslangic.myapp.Controller;

import baslangic.myapp.Kaynak.Model;
import baslangic.myapp.Repository.modelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/modeller")
public class modelController {
     modelRepository modelRep;

     @Autowired
    public modelController(modelRepository modelRep) {
        this.modelRep = modelRep;
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Model> getAll()
    {
        return modelRep.findAll();
    }


  /*  @RequestMapping(value = "/create",method = RequestMethod.POST)
    public List<Model> create(@RequestBody Model model)
    {
        modelRep.save(model);
        return modelRep.findAll();
    }

*/
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public List<Model>remove(@PathVariable int id)
    {
        modelRep.deleteById(id);
        return modelRep.findAll();
    }
}
