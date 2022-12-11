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

    
    @Query(
    		value= "SELECT COUNT(*) FROM Favoris F WHERE F.codeUtilisateur = :codeUtilisateur AND F.codeCircuit = :codeCircuit",
    		nativeQuery = true)
    int isFavoris(Long codeUtilisateur, Long codeCircuit);
    
    @Query(
    		value= "SELECT F.code FROM Favoris F WHERE F.codeUtilisateur = :codeUtilisateur AND F.codeCircuit = :codeCircuit",
    		nativeQuery = true)
    Long getId(Long codeUtilisateur, Long codeCircuit);
    
    @Query(
    		value="SELECT * FROM Favoris F WHERE F.codeCircuit = :codeCircuit",
    		nativeQuery = true)
    List<Favoris> findFavorisByCircuit(Long codeCircuit);
}
