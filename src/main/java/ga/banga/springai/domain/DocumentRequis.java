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
    private Demande demande;

    @Column(nullable = false)
    private boolean obligatoire = true;

    // Constructeurs, Getters, Setters



    public DocumentRequis() {
    }

    public boolean isObligatoire() {
        return obligatoire;
    }

    public void setObligatoire(boolean obligatoire) {
        this.obligatoire = obligatoire;
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

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    @Override
    public String toString() {
        return "DocumentRequis{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", demande=" + demande +
                ", obligatoire=" + obligatoire +
                '}';
    }
}
