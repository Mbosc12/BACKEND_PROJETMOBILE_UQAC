package fr.boscmalo.uqac.book2roadbackend.Controller;

import fr.boscmalo.uqac.book2roadbackend.Model.Utilisateur;
import fr.boscmalo.uqac.book2roadbackend.Model.Ville;
import fr.boscmalo.uqac.book2roadbackend.Repository.UtilisateurRepository;
import fr.boscmalo.uqac.book2roadbackend.Repository.VilleRepository;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtilisateurController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private VilleRepository villeRepository;

    /**
     * Get all users
     * @return
     */
    @GetMapping("/utilisateurs")
    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    /**
     * Get user info by his pseudo
     * @param user
     * @return
     */
    @GetMapping("/utilisateur")
    public Utilisateur getUser(@RequestParam(value="user") String user) {
        return utilisateurRepository.getUser(user);
    }

    /**
     * Check if user exist
     * @param user
     * @return
     */
    @GetMapping("/login/checkUser")
    public boolean userExist(@RequestParam(value="user") String user) {
        return (utilisateurRepository.userExist(user) > 0);
    }

    /**
     * Check if information are true
     * @param user
     * @param mdp
     * @return
     */
    @GetMapping("/login")
    public boolean userConnection(@RequestParam(value="user") String user, @RequestParam(value="mdp") String mdp) {
        return (utilisateurRepository.connection(user, mdp) > 0);
    }

    @PostMapping("/utilisateur")
    public void userRegister(@RequestBody Utilisateur u) {
        Ville v = villeRepository.findVilleById(12000);
        u.setVille(v);
        utilisateurRepository.save(u);
    }

}
