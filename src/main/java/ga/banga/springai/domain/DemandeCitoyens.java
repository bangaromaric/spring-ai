package ga.banga.springai.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "demande_citoyens")
public class DemandeCitoyens {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomCitoyen;

    @Column(nullable = false)
    private String telephone; // Peut être utilisé pour l'envoi de SMS

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message; // Demande initiale du citoyen

    @ManyToOne
    @JoinColumn(name = "demande_id")
    private Demandes demande; // Démarche identifiée par l'IA

    @Column(columnDefinition = "TEXT")
    private String reponseIA; // Réponse fournie par l'IA

    @Column
    private String urlAudioReponse; // Lien vers la réponse audio générée

    @Column(nullable = false)
    private LocalDateTime dateDemande = LocalDateTime.now();

    // Constructeurs, Getters, Setters

    public DemandeCitoyens() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomCitoyen() {
        return nomCitoyen;
    }

    public void setNomCitoyen(String nomCitoyen) {
        this.nomCitoyen = nomCitoyen;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Demandes getDemande() {
        return demande;
    }

    public void setDemande(Demandes demande) {
        this.demande = demande;
    }

    public String getReponseIA() {
        return reponseIA;
    }

    public void setReponseIA(String reponseIA) {
        this.reponseIA = reponseIA;
    }

    public String getUrlAudioReponse() {
        return urlAudioReponse;
    }

    public void setUrlAudioReponse(String urlAudioReponse) {
        this.urlAudioReponse = urlAudioReponse;
    }

    public LocalDateTime getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDateTime dateDemande) {
        this.dateDemande = dateDemande;
    }
}

