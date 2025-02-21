package ga.banga.springai.repository;

import ga.banga.springai.domain.Demandes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DemandeRepository extends JpaRepository<Demandes, Long> {
    Demandes findByNomIgnoreCase(String nom);
}