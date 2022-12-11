package fr.boscmalo.uqac.book2roadbackend.Repository;

import fr.boscmalo.uqac.book2roadbackend.Model.Circuit;
import fr.boscmalo.uqac.book2roadbackend.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query(
            value = "SELECT I.code, I.lien, I.codecircuit FROM Image I WHERE I.Codecircuit = :code",
            nativeQuery = true)
    List<Image> getImageByCircuit(Long code);

}
