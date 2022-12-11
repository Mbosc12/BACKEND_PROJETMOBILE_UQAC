package fr.boscmalo.uqac.book2roadbackend.Controller;

import fr.boscmalo.uqac.book2roadbackend.Model.Avis;
import fr.boscmalo.uqac.book2roadbackend.Model.Circuit;
import fr.boscmalo.uqac.book2roadbackend.Model.Reservation;
import fr.boscmalo.uqac.book2roadbackend.Repository.AvisRepository;
import fr.boscmalo.uqac.book2roadbackend.Repository.CircuitRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AvisController {
    @Autowired
    private AvisRepository avisRepository;
   
    @Autowired
    private CircuitRepository circuitRepository;

    @RequestMapping(value="/avis/{codeCircuit}", method = RequestMethod.GET)
    public List<Avis> getAll(@PathVariable Long codeCircuit) {
        return avisRepository.findAvisFromCircuit(codeCircuit);
    }
    
    @RequestMapping(value="/avis", method= RequestMethod.GET)
    public List<Avis> getAllByUser(@RequestParam(value="user") int codeUser) {
    	List<Circuit> listCircuit = circuitRepository.findCircuitsByUser(codeUser);
    	List<Avis> avis = new ArrayList<>();
    	
    	for(Circuit c : listCircuit) {
    		for(Avis a : avisRepository.findAvisFromCircuit(c.getCode())) {
    			avis.add(a);
    		}
    	}
        return avis;
    }

    @RequestMapping(value="/avis/average/{codeCircuit}", method = RequestMethod.GET)
    public Float getAverage(@PathVariable Long codeCircuit) { return avisRepository.findAverage(codeCircuit); }

    @RequestMapping(value="/avis", method = RequestMethod.POST)
    public void setAvis(@RequestBody Avis avis) {
    	avisRepository.save(avis);
    }
    
    @RequestMapping(value="/avis/nb", method = RequestMethod.GET)
    public int getNbAvis(@RequestParam(value="user") int codeUser) {
    	List<Circuit> listCircuit = circuitRepository.findCircuitsByUser(codeUser);
    	if(listCircuit.size() == 0 ) { return 0; }
    	int nbAvis = 0;
    	for(Circuit c : listCircuit) {
    		nbAvis += avisRepository.findNbAvis(c.getCode());
    	}
    	    	
    	return nbAvis;
    }
    
    @RequestMapping(value="/avis/user", method = RequestMethod.GET)
    public Float getAverageByUser(@RequestParam(value="user") int codeUser) {
    	List<Circuit> listCircuit = circuitRepository.findCircuitsByUser(codeUser);
    	if(listCircuit.size() == 0 ) { return null; }
    	Float f = 0f;
    	int nbAvis = 0;
    	for(Circuit c : listCircuit) {
    		f += (avisRepository.findScore(c.getCode()) != null) ? avisRepository.findScore(c.getCode()) : 0;
    		nbAvis += avisRepository.findNbAvis(c.getCode());
    	}
    	    	
    	return f/nbAvis;
    }

    @RequestMapping(value="/avis", method = RequestMethod.DELETE)
    public void removeAvis(@RequestBody Avis avis) {
        avisRepository.delete(avis);
    }
}
