package fr.boscmalo.uqac.book2roadbackend.Controller;

import fr.boscmalo.uqac.book2roadbackend.Model.Tarif;
import fr.boscmalo.uqac.book2roadbackend.Repository.TarifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TarifController {
    @Autowired
    private TarifRepository tarifRepository;

    @GetMapping("/tarifs")
    public List<Tarif> getTarifByCodeCircuit(@RequestParam(value="code") Integer code) {
        return tarifRepository.findAll();
    }

    @RequestMapping(value= "/tarifs", method = RequestMethod.POST)
    public void getTarifByCodeCircuit(@RequestBody Tarif tarif) {
        tarifRepository.save(tarif);
    }


}
