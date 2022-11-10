package fr.boscmalo.uqac.book2roadbackend.Controller;

import fr.boscmalo.uqac.book2roadbackend.Model.Ville;
import fr.boscmalo.uqac.book2roadbackend.Repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VilleController {
    @Autowired
    private VilleRepository villeRepository;

    @GetMapping("/villes")
    public List<Ville> getAll() {
        return villeRepository.findAll();
    }
}
