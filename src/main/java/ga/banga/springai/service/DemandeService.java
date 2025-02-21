package ga.banga.springai.service;

import ga.banga.springai.domain.Demandes;
import ga.banga.springai.domain.DocumentRequis;
import ga.banga.springai.repository.DemandeRepository;
import ga.banga.springai.repository.DocumentRequisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeService {

    private final DemandeRepository demandeRepository;

    private final DocumentRequisRepository documentRequisRepository;

    public DemandeService(DemandeRepository demandeRepository, DocumentRequisRepository documentRequisRepository) {
        this.demandeRepository = demandeRepository;
        this.documentRequisRepository = documentRequisRepository;
    }

    public Demandes ajouterDemandeAvecDocuments(String nom, String description, List<String> documents) {
        Demandes demande = new Demandes();
        demande.setNom(nom);
        demande.setDescription(description);
        demande = demandeRepository.save(demande);

        for (String doc : documents) {
            DocumentRequis document = new DocumentRequis();
            document.setNom(doc);
            document.setDemande(demande);
            documentRequisRepository.save(document);
        }

        return demande;
    }

    public void initData() {
        this.ajouterDemandeAvecDocuments(
                "Passeport",
                "Démarche pour obtenir un passeport gabonais.",
                List.of("Acte de naissance", "Justificatif de domicile", "Photos d'identité")
        );
    }
}
