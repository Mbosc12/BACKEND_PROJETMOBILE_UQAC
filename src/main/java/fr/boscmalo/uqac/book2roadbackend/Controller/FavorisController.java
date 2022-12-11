package fr.boscmalo.uqac.book2roadbackend.Controller;

import fr.boscmalo.uqac.book2roadbackend.Model.Circuit;
import fr.boscmalo.uqac.book2roadbackend.Model.Departement;
import fr.boscmalo.uqac.book2roadbackend.Model.Favoris;
import fr.boscmalo.uqac.book2roadbackend.Repository.CircuitRepository;
import fr.boscmalo.uqac.book2roadbackend.Repository.DepartementRepository;
import fr.boscmalo.uqac.book2roadbackend.Repository.FavorisRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class FavorisController {
    @Autowired
    FavorisRepository favorisRepository;
    
    @Autowired
    CircuitRepository circuitRepository;

    @GetMapping("/favoris/{codeUtilisateur}")
    public List<Circuit> getCircuitsFavoris(@PathVariable Long codeUtilisateur) {
        List<Long> codeCircuit = favorisRepository.findFavoris(codeUtilisateur);
    	
        List<Circuit> circuits = new ArrayList<>();
        
        for(Long l : codeCircuit) {
        	Circuit c = circuitRepository.findCircuitsById(l);
        	circuits.add(c);
        }
        
    	return circuits;
    }
    
    @RequestMapping(value="/favoris", method = RequestMethod.POST)
    public void setFavoris(@RequestBody Favoris fav) {
    	favorisRepository.save(fav);
    }
    
    @RequestMapping(value="/favoris", method = RequestMethod.DELETE)
    public void removeFavoris(@RequestBody Favoris fav) {
    	favorisRepository.delete(fav);
    }
    
    @RequestMapping(value="/favoris/check", method = RequestMethod.GET)
    public Long isFavoris(@RequestParam(value="codeUtilisateur") Long codeUtilisateur, @RequestParam(value="codeCircuit") Long codeCircuit) {
    	return (favorisRepository.isFavoris(codeUtilisateur, codeCircuit) == 1) ? favorisRepository.getId(codeUtilisateur, codeCircuit) : null;
    }
    

}
