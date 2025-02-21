package ga.banga.springai.domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "demandes")
public class Demandes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom; // Nom de la démarche (ex: Passeport, Carte d'identité)

    @Column(columnDefinition = "TEXT")
    private String description; // Détails sur la démarche

    @OneToMany(mappedBy = "demande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentRequis> documentsRequis = new ArrayList<>();

    // Constructeurs, Getters, Setters


    @Override
    public String toString() {
        return "Demandes{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", documentsRequis=" + documentsRequis +
                '}';
    }

    public Demandes() {
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

    public List<DocumentRequis> getDocumentsRequis() {
        return documentsRequis;
    }

    public void setDocumentsRequis(List<DocumentRequis> documentsRequis) {
        this.documentsRequis = documentsRequis;
    }
}
