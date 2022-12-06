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
	
}
