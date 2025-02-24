package ga.banga.springai.service.dto;

import ga.banga.springai.domain.Demandes;
import ga.banga.springai.domain.DocumentRequis;

import java.util.List;
import java.util.stream.Collectors;

public class DemandesDTO {
    private String nom;
    private String description;
    private List<String> documentsRequis;

    public DemandesDTO(Demandes demande) {
        this.nom = demande.getNom();
        this.description = demande.getDescription();
        this.documentsRequis = demande.getDocumentsRequis()
                .stream()
                .map(DocumentRequis::getNom)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "DemandesDTO{" +
                "nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", documentsRequis=" + documentsRequis +
                '}';
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

    public List<String> getDocumentsRequis() {
        return documentsRequis;
    }

    public void setDocumentsRequis(List<String> documentsRequis) {
        this.documentsRequis = documentsRequis;
    }
}
