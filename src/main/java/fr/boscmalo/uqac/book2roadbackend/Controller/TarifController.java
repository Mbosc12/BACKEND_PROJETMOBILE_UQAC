package fr.boscmalo.uqac.book2roadbackend.Controller;

import fr.boscmalo.uqac.book2roadbackend.Model.Region;
import fr.boscmalo.uqac.book2roadbackend.Model.Tarif;
import fr.boscmalo.uqac.book2roadbackend.Repository.RegionRepository;
import fr.boscmalo.uqac.book2roadbackend.Repository.TarifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class TarifController {

    @Autowired
    private TarifRepository tarifRepository;

    @GetMapping("/tarifs")
    public List<Tarif> getAll() {
        return tarifRepository.findAll();
    }

}
