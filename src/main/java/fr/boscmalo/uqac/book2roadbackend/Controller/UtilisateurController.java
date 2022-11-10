package fr.boscmalo.uqac.book2roadbackend.Controller;

import fr.boscmalo.uqac.book2roadbackend.Model.Utilisateur;
import fr.boscmalo.uqac.book2roadbackend.Repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UtilisateurController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    /**
     * Get all users
     * @return
     */
    @GetMapping("/utilisateurs")
    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
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
}
