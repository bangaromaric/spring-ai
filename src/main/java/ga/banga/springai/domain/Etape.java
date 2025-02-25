package ga.banga.springai.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "etape")
public class Etape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int ordre; // Ordre de l'étape (1, 2, 3...)

    @Column(nullable = false)
    private String titre; // Ex: "Remplir le formulaire"

    @Column(columnDefinition = "TEXT")
    private String details; // Détails de l’étape

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "demande_id", nullable = false)
    private Demande demande;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    @Override
    public String toString() {
        return "Etape{" +
                "id=" + id +
                ", ordre=" + ordre +
                ", titre='" + titre + '\'' +
                ", details='" + details + '\'' +
                ", demande=" + demande +
                '}';
    }
}