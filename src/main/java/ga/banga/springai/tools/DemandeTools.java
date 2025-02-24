package ga.banga.springai.tools;

import ga.banga.springai.domain.Demandes;
import ga.banga.springai.repository.DemandeRepository;
import ga.banga.springai.service.dto.DemandesDTO;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;

public class DemandeTools {


    @Tool(description = "Récupère les informations nécessaires pour obtenir un type de document au Gabon")

    DemandesDTO obtenirInformations(@ToolParam(description = "le type de document") String typeDocument, ToolContext toolContext) {
        String typeDocumentNormalized = normalizeString(typeDocument);
        DemandeRepository  demandeRepository  =  (DemandeRepository) toolContext.getContext().get("demandeRepository");
        Demandes demande = demandeRepository.findByNomIgnoreCase(typeDocumentNormalized);
//        System.out.println("******** "+demande);
        if (demande == null) {
            return null;
        }
        return new DemandesDTO(demande);
    }

    private  String normalizeString(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[\\p{M}]", "") // Supprime les accents
                .replace("’", "'") // Remplace l’apostrophe typographique
                .toLowerCase();
    }
}
