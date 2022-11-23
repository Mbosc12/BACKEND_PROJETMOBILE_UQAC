package fr.boscmalo.uqac.book2roadbackend.Repository;

import fr.boscmalo.uqac.book2roadbackend.Model.Circuit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CircuitRepository extends JpaRepository<Circuit, Long> {

    @Query(
            value = "SELECT * FROM Circuit C WHERE C.code = ?1",
            nativeQuery = true)
    Circuit findCircuitsById(Long param);

    @Query(
            value = "SELECT * FROM Circuit C WHERE UPPER(C.Nom) LIKE %:nom%",
            nativeQuery = true)
    List<Circuit> findCircuitsByName(String nom);

    @Query(
            value = "SELECT * FROM Circuit C WHERE C.Codeproprietaire = :code",
            nativeQuery = true)
    List<Circuit> findCircuitsByUser(int code);

}