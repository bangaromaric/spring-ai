package ga.banga.springai.tools;

import ga.banga.springai.domain.Demande;
import ga.banga.springai.domain.ProcedureType;
import ga.banga.springai.domain.TypeDocument;
import ga.banga.springai.repository.DemandeRepository;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;

public class DemandeTools {

    @Tool(description = "Récupère les informations nécessaires pour obtenir un type de document au Gabon")
    Demande obtenirInformations(@ToolParam(description = "le type de document") TypeDocument typeDocument, ToolContext toolContext) {
        System.out.println("type******** "+typeDocument);
        String typeDocumentNormalized = normalizeString(typeDocument.name());
        DemandeRepository  demandeRepository  =  (DemandeRepository) toolContext.getContext().get("demandeRepository");
//        Demande demande = demandeRepository.findByNomIgnoreCase(typeDocumentNormalized);
        Demande demande = demandeRepository.findByNomWithAllCollections(typeDocumentNormalized,ProcedureType.NOUVELLE);

        if (demande == null) {
            return null;
        }
        return demande;
    }

    @Tool(description = "Récupère les informations nécessaires pour renouveler un type de document au Gabon")
    Demande renouvelleInformations(@ToolParam(description = "le type de document") TypeDocument typeDocument, ToolContext toolContext) {
        System.out.println("type******** "+typeDocument+"-----renouvelement");
        String typeDocumentNormalized = normalizeString(typeDocument.name());
        DemandeRepository  demandeRepository  =  (DemandeRepository) toolContext.getContext().get("demandeRepository");
        Demande demande = demandeRepository.findByNomWithAllCollections(typeDocumentNormalized, ProcedureType.RENOUVELLEMENT);

        if (demande == null) {
            return null;
        }
        return demande;
    }

    private  String normalizeString(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[\\p{M}]", "") // Supprime les accents
                .replace("’", "'") // Remplace l’apostrophe typographique
                .toLowerCase();
    }
}
