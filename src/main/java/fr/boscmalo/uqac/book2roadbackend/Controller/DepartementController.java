package fr.boscmalo.uqac.book2roadbackend.Controller;

import fr.boscmalo.uqac.book2roadbackend.Model.Departement;
import fr.boscmalo.uqac.book2roadbackend.Repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class DepartementController {
    @Autowired
    DepartementRepository departementRepository;

    @GetMapping("/departement/{numero}")
    public Departement getDepartement(@PathVariable Long numero) {
        return departementRepository.findDepartementById(numero);
    }

    @RequestMapping(value="/departement", method=RequestMethod.GET)
    public Departement getDepartementByNumero(Long numero) {
        System.out.println(departementRepository);
        return this.departementRepository.findDepartementById(numero);
    }
}
