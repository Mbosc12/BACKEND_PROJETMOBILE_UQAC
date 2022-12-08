package fr.boscmalo.uqac.book2roadbackend.Controller;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import fr.boscmalo.uqac.book2roadbackend.Model.*;
import fr.boscmalo.uqac.book2roadbackend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CircuitController {
    @Autowired
    private CircuitRepository circuitRepository;
    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired AvisRepository avisRepository;

    @Autowired UtilisateurRepository utilisateurRepository;

    /**
     * Get all tracks
     * @return
     */
    @GetMapping("/circuits")
    public @ResponseBody Page<Circuit> getCircuit(
            @QuerydslPredicate(root = Circuit.class) Predicate predicate,
            @RequestParam(name="dateDebut", required = false) Date dateDebut ,
            @RequestParam(name="dateFin", required = false)  Date dateFin,
            @PageableDefault(size = 5, sort="Code") Pageable pageable) {

        System.out.println(predicate);
        return circuitRepository.findAll(predicate, pageable);
    }

    /**
     * Get track by Id
     * @param code
     * @return
     */
    @RequestMapping(value="/circuits/{code}", method = RequestMethod.GET)
    public Circuit getById(@PathVariable Long code) {
        return circuitRepository.findCircuitsById(code);
    }

    /**
     * Get track by name
     * @param nom
     * @return
     */
    @RequestMapping(value="/circuits/search", method = RequestMethod.GET)
    public List<Circuit> getByName(@RequestParam(value="nom") String nom) {
        return circuitRepository.findCircuitsByName(nom.toUpperCase());
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

    @RequestMapping(value="/circuits/rating", method = RequestMethod.GET)
    public List<Circuit> getCircuitsByRating() {
        List<Long> codeCircuit = avisRepository.getBestCircuit();
        List<Circuit> circuits = new ArrayList<>();
        for(Long circuit : codeCircuit) {
            circuits.add(circuitRepository.findCircuitsById(circuit));
        }

        return circuits;
    }

    @RequestMapping(value="/circuits/region/{codeUtilisateur}", method = RequestMethod.GET)
    public List<Circuit> getCircuitsByRegion(@PathVariable Long codeUtilisateur) {
        Utilisateur u = utilisateurRepository.getById(codeUtilisateur);
        Long codeRegion = u.getVille().getDepartement().getRegion().getCode();
        return circuitRepository.findCircuitsByRegion(codeRegion);
    }

    /**
     * Create or update a track
     * @param c
     */
    @RequestMapping(value="/circuits", method = RequestMethod.POST)
    public long setCircuit(@RequestBody Circuit c) {
        Ville v = c.getVille();

        v.setLongitude("NaN"); v.setLatitude("NaN");

        Departement d = departementRepository.findDepartementById(Long.valueOf(String.valueOf(v.getCodePostal()).substring(0, 2)));
        v.setDepartement(d);
        villeRepository.save(v);

        c.setCodeRegion(d.getRegion().getCode());
        circuitRepository.save(c);
        return c.getCode();
    }

    @RequestMapping(value="/circuits", method = RequestMethod.DELETE)
    public void removeCircuit(@RequestBody Circuit c) {
        circuitRepository.delete(c);
    }
}
