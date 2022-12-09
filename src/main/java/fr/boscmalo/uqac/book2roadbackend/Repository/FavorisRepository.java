package fr.boscmalo.uqac.book2roadbackend.Repository;

import fr.boscmalo.uqac.book2roadbackend.Model.Circuit;
import fr.boscmalo.uqac.book2roadbackend.Model.Departement;
import fr.boscmalo.uqac.book2roadbackend.Model.Favoris;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FavorisRepository extends JpaRepository<Favoris, Long> {
    @Query(
            value = "SELECT F.codeCircuit FROM Favoris F WHERE F.codeUtilisateur = :codeUtilisateur",
            nativeQuery = true)
    List<Long> findFavoris(Long codeUtilisateur);

}
