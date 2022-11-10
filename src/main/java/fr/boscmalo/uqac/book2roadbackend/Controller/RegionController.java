package fr.boscmalo.uqac.book2roadbackend.Controller;

import fr.boscmalo.uqac.book2roadbackend.Model.Region;
import fr.boscmalo.uqac.book2roadbackend.Repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionController {
    @Autowired
    private RegionRepository regionRepository;

    @GetMapping("/regions")
    public List<Region> getAll() {
        return regionRepository.findAll();
    }
}
