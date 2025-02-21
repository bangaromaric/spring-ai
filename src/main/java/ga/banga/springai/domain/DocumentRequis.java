package ga.banga.springai.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "document_requis")
public class DocumentRequis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom; // Nom du document (ex: Acte de naissance)

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "demande_id", nullable = false)
    private Demandes demande;

    // Constructeurs, Getters, Setters


    public DocumentRequis() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Demandes getDemande() {
        return demande;
    }

    public void setDemande(Demandes demande) {
        this.demande = demande;
    }
}
