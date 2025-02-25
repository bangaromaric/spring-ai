package ga.banga.springai.domain;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "demande")
public class Demande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom; // Nom de la démarche (ex: Passeport, Carte d'identité)

    @Enumerated(EnumType.STRING)
    private ProcedureType type;

    @Column(columnDefinition = "TEXT")
    private String description; // Détails sur la démarche


    @Column(name = "delai_moyen")
    private String delaiMoyen;

    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL)
    private Set<DocumentRequis> documentsRequis = new HashSet<>();

    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL)
    private Set<Etape> etapes = new HashSet<>();

    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL)
    private Set<Cout> couts = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "demande_centre",
            joinColumns = @JoinColumn(name = "demande_id"),
            inverseJoinColumns = @JoinColumn(name = "centre_id")
    )
    private Set<CentreService> centres = new HashSet<>();

    // Constructeurs, Getters, Setters


    public ProcedureType getType() {
        return type;
    }

    public void setType(ProcedureType type) {
        this.type = type;
    }

    public Set<DocumentRequis> getDocumentsRequis() {
        return documentsRequis;
    }

    public void setDocumentsRequis(Set<DocumentRequis> documentsRequis) {
        this.documentsRequis = documentsRequis;
    }

    public Set<Etape> getEtapes() {
        return etapes;
    }

    public void setEtapes(Set<Etape> etapes) {
        this.etapes = etapes;
    }

    public Set<Cout> getCouts() {
        return couts;
    }

    public void setCouts(Set<Cout> couts) {
        this.couts = couts;
    }

    public Set<CentreService> getCentres() {
        return centres;
    }

    public void setCentres(Set<CentreService> centres) {
        this.centres = centres;
    }

    public String getDelaiMoyen() {
        return delaiMoyen;
    }

    public void setDelaiMoyen(String delai) {
        this.delaiMoyen = delai;
    }



    public Demande() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Demande{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", delaiMoyen='" + delaiMoyen + '\'' +
                ", documentsRequis=" + documentsRequis +
                ", etapes=" + etapes +
                ", couts=" + couts +
                ", centres=" + centres +
                '}';
    }
}
