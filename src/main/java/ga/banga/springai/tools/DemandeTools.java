package ga.banga.springai.tools;

import ga.banga.springai.domain.Demandes;
import ga.banga.springai.repository.DemandeRepository;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

import java.text.Normalizer;

public class DemandeTools {


    @Tool(description = "Récupère les informations nécessaires pour obtenir un type de document au Gabon")
    Demandes obtenirInformations(@ToolParam(description = "le type de document") String typeDocument, ToolContext toolContext) {
        String typeDocumentNormalized = normalizeString(typeDocument);
        DemandeRepository  demandeRepository  =  (DemandeRepository) toolContext.getContext().get("demandeRepository");
        return demandeRepository.findByNomIgnoreCase(typeDocumentNormalized);
    }

    private  String normalizeString(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[\\p{M}]", "") // Supprime les accents
                .replace("’", "'") // Remplace l’apostrophe typographique
                .toLowerCase();
    }
}
