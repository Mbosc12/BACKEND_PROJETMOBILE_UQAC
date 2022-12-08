package fr.boscmalo.uqac.book2roadbackend.Repository;

import fr.boscmalo.uqac.book2roadbackend.Model.Circuit;
import fr.boscmalo.uqac.book2roadbackend.Model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    @Query(
            value = "SELECT COUNT(*) FROM Utilisateur U WHERE U.Pseudo = :user",
            nativeQuery = true)
    int userExist(String user);

    @Query(
            value = "SELECT COUNT(*) FROM Utilisateur U WHERE U.Pseudo = :user AND U.Motdepasse = :mdp",
            nativeQuery = true)
    int connection(String user, String mdp);

    @Query(
            value = "SELECT * FROM Utilisateur U WHERE U.pseudo = :pseudo",
            nativeQuery = true)
    Utilisateur getUser(String pseudo);

    @Query(
            value = "SELECT * FROM Utilisateur U WHERE U.Code = :code",
            nativeQuery = true)
    Utilisateur getById(Long code);

}
