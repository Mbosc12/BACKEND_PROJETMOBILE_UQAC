package fr.boscmalo.uqac.book2roadbackend.Controller;

import fr.boscmalo.uqac.book2roadbackend.Model.Avis;
import fr.boscmalo.uqac.book2roadbackend.Model.Reservation;
import fr.boscmalo.uqac.book2roadbackend.Repository.AvisRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AvisController {
    @Autowired
    private AvisRepository avisRepository;

    @RequestMapping(value="/avis/{codeCircuit}", method = RequestMethod.GET)
    public List<Avis> getAll(@PathVariable Long codeCircuit) {
        return avisRepository.findAvisFromCircuit(codeCircuit);
    }
    
    @RequestMapping(value="/avis", method= RequestMethod.GET)
    public List<Avis> getAllByUser(@RequestParam(value="user") Long codeUser) {
        return avisRepository.findAvisByUser(codeUser);
    }


    @RequestMapping(value="/avis", method = RequestMethod.POST)
    public void setAvis(@RequestBody Avis avis) {
    	avisRepository.save(avis);
    }

    @RequestMapping(value="/avis", method = RequestMethod.DELETE)
    public void removeAvis(@RequestBody Avis avis) {
        avisRepository.delete(avis);
    }
}
