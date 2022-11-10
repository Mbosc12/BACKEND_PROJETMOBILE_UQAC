package fr.boscmalo.uqac.book2roadbackend.Controller;

import fr.boscmalo.uqac.book2roadbackend.Model.Departement;
import fr.boscmalo.uqac.book2roadbackend.Repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartementController {
    @Autowired
    private DepartementRepository departementRepository;

    @GetMapping("/departements")
    public List<Departement> getAll() {
        return departementRepository.findAll();
    }
}
