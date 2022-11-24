package fr.boscmalo.uqac.book2roadbackend.Repository;

import fr.boscmalo.uqac.book2roadbackend.Model.Circuit;
import fr.boscmalo.uqac.book2roadbackend.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(
            value = "SELECT * FROM Reservation R WHERE R.codeCircuit = ?1",
            nativeQuery = true)
    List<Reservation> findReservationByCircuit (Long codeCircuit);

    @Query(
            value = "SELECT * FROM Reservation R WHERE R.Codeutilisateur = ?1",
            nativeQuery = true)
    List<Reservation> findReservationByUser (Long codeUtilisateur);

    @Query(
            value = "SELECT COUNT(*) FROM RESERVATION R WHERE R.codeCircuit = :codeCircuit AND " +
                    " ((R.dateDebut >= :dateDebut AND R.dateDebut <= :dateFin) OR" +
                    " ((R.dateFin >= :dateDebut AND R.dateFin <= :dateFin)))",
            nativeQuery = true)
    int findReservationByIdAndDate(Long codeCircuit, Date dateDebut, Date dateFin);


}
