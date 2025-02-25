package ga.banga.springai.repository;

import ga.banga.springai.domain.Demande;
import ga.banga.springai.domain.ProcedureType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DemandeRepository extends JpaRepository<Demande, Long> {
    Demande findByNomIgnoreCase(String nom);

    @Query("SELECT d FROM Demande d " +
            "LEFT JOIN FETCH d.documentsRequis " +
            "LEFT JOIN FETCH d.etapes " +
            "LEFT JOIN FETCH d.couts " +
            "LEFT JOIN FETCH d.centres " +
            "WHERE UPPER(d.nom) = UPPER(:nom)")
    Demande findByNomWithAllCollections(@Param("nom") String nom);

    @Query("SELECT d FROM Demande d " +
            "LEFT JOIN FETCH d.documentsRequis " +
            "LEFT JOIN FETCH d.etapes " +
            "LEFT JOIN FETCH d.couts " +
            "LEFT JOIN FETCH d.centres " +
            "WHERE UPPER(d.libelle) = UPPER(:libelle) and d.type=:type")
    Demande findByNomWithAllCollections(@Param("libelle") String libelle, ProcedureType type);

}