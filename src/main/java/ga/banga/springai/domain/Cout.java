package ga.banga.springai.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cout")
public class Cout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // Ex: Timbre fiscal, Frais de dossier

    @Column(nullable = false)
    private BigDecimal montant;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    @Override
    public String toString() {
        return "Cout{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", montant=" + montant +
                ", demande=" + demande +
                '}';
    }
}
