package fr.boscmalo.uqac.book2roadbackend.Controller;

import fr.boscmalo.uqac.book2roadbackend.Model.Departement;
import fr.boscmalo.uqac.book2roadbackend.Model.Ville;
import fr.boscmalo.uqac.book2roadbackend.Repository.DepartementRepository;
import fr.boscmalo.uqac.book2roadbackend.Repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VilleController {
    @Autowired
    VilleRepository villeRepository;

    @GetMapping("/villes")
    public List<Ville> getAll() {
        return villeRepository.findAll();
    }

    @RequestMapping(value = "/villes", method = RequestMethod.POST)
    public void setVille(@RequestBody Ville v) {
        Long id = Long.valueOf(String.valueOf(v.getCodePostal()).substring(0, 2));
        v.setLatitude("NaN");
        v.setLongitude("NaN");

        villeRepository.save(v);
    }
}
