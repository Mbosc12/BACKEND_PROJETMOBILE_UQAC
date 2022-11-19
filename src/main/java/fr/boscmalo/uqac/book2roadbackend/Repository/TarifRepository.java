package fr.boscmalo.uqac.book2roadbackend.Repository;

import fr.boscmalo.uqac.book2roadbackend.Model.Image;
import fr.boscmalo.uqac.book2roadbackend.Model.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarifRepository extends JpaRepository<Tarif, Long> {

    @Query(
            value = "SELECT T.prix, T.codecircuit FROM Tarif T WHERE T.Codecircuit = :code",
            nativeQuery = true)
    Tarif getTarifByCircuit(Integer code);

}
