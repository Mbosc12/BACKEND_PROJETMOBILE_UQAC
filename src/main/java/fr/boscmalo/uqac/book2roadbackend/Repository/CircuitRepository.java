package fr.boscmalo.uqac.book2roadbackend.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import fr.boscmalo.uqac.book2roadbackend.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.MultiValueBinding;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.sql.Date;
import java.util.*;

@Repository
public interface CircuitRepository extends PagingAndSortingRepository<Circuit, Long>, QuerydslPredicateExecutor<Circuit>,
QuerydslBinderCustomizer<QCircuit> {

    @Override
    default void customize(QuerydslBindings bindings, QCircuit circuit) {
        bindings.bind(circuit.nom).first(new SingleValueBinding<StringPath, String>() {
            @Override
            public Predicate bind(StringPath path, String value) {
                return path.contains(value);
            }
        });

        bindings.bind(circuit.codeRegion).first(new SingleValueBinding<NumberPath<Long>, Long>() {
            @Override
            public Predicate bind(NumberPath<Long> path, Long value) {
                return path.eq(value);
            }
        });

        bindings.bind(circuit.tarif).first(new SingleValueBinding<NumberPath<Float>, Float>() {
            @Override
            public Predicate bind(NumberPath<Float> path, Float value) {
                return path.lt(value).or(path.eq(value));
            }
        });
    }

        @Query(
            value = "SELECT * FROM Circuit C WHERE C.code = ?1",
            nativeQuery = true)
    Circuit findCircuitsById(Long param);

    @Query(
            value = "SELECT * FROM Circuit C WHERE C.region = ?1",
            nativeQuery = true)
    List<Circuit> findCircuitsByRegion(Long param);

    @Query(
            value = "SELECT * FROM Circuit C WHERE UPPER(C.Nom) LIKE %:nom%",
            nativeQuery = true)
    List<Circuit> findCircuitsByName(String nom);

    @Query(
            value = "SELECT * FROM Circuit C WHERE C.Codeproprietaire = :code",
            nativeQuery = true)
    List<Circuit> findCircuitsByUser(int code);

}