package fr.boscmalo.uqac.book2roadbackend.Repository;

import fr.boscmalo.uqac.book2roadbackend.Model.Avis;
import fr.boscmalo.uqac.book2roadbackend.Model.Reservation;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisRepository extends JpaRepository<Avis, Long> {
	
    @Query(
            value = "SELECT * FROM Avis A WHERE A.codeCircuit = ?1",
            nativeQuery = true)
    List<Avis> findAvisFromCircuit (Long codeCircuit);

    @Query(
            value = "SELECT * FROM Avis A WHERE A.Codeutilisateur = :codeUtilisateur",
            nativeQuery = true)
    List<Avis> findAvisByUser (Long codeUtilisateur);
    
    @Query(
            value= "SELECT SUM(A.Etoiles)/COUNT(*) FROM AVIS A WHERE A.Codecircuit = :codeCircuit",
            nativeQuery = true)
    Float findAverage(Long codeCircuit);

    @Query(
            value= "SELECT codeCircuit FROM (SELECT SUM(A.Etoiles)/COUNT(*) AS Moyenne, A.codeCircuit FROM AVIS A GROUP BY codeCircuit ORDER BY Moyenne DESC)",
            nativeQuery = true)
    List<Long> getBestCircuit();
}
