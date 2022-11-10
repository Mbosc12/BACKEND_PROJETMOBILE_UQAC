package fr.boscmalo.uqac.book2roadbackend.Controller;

import fr.boscmalo.uqac.book2roadbackend.Model.Circuit;
import fr.boscmalo.uqac.book2roadbackend.Repository.CircuitRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CircuitController {
    @Autowired
    private CircuitRepository circuitRepository;

    /**
     * Get all tracks
     * @return
     */
    @GetMapping("/circuits")
    public List<Circuit> getAll() {
        return circuitRepository.findAll();
    }

    /**
     * Get track by Id
     * @param code
     * @return
     */
    @RequestMapping(value="/circuits/{code}", method = RequestMethod.GET)
    public List<Circuit> getById(@PathVariable Long code) {
        return circuitRepository.findCircuitsById(code);
    }

    /**
     * Get track by name
     * @param nom
     * @return
     */
    @RequestMapping(value="/circuits/search", method = RequestMethod.GET)
    public List<Circuit> getByName(@RequestParam(value="nom") String nom) {
        return circuitRepository.findCircuitsByName(nom);
    }

    /**
     * Get track by user
     * @param user
     * @return
     */
    @RequestMapping(value="/circuits/user", method = RequestMethod.GET)
    public List<Circuit> getByUser(@RequestParam(value="user") int user) {
        return circuitRepository.findCircuitsByUser(user);
    }

    /**
     * Create or update a track
     * @param c
     */
    @RequestMapping(value="/circuits", method = RequestMethod.POST)
    public void setCircuit(@RequestBody Circuit c) {
        circuitRepository.save(c);
    }

}
