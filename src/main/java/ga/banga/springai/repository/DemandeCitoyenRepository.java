package ga.banga.springai.repository;

import ga.banga.springai.domain.DemandeCitoyens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeCitoyenRepository extends JpaRepository<DemandeCitoyens, Long> {
}