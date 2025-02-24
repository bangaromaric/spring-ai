package ga.banga.springai;

import ga.banga.springai.domain.Demandes;
import ga.banga.springai.repository.DemandeRepository;
import ga.banga.springai.tools.DemandeTools;
import ga.banga.springai.tools.DateTimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.text.Normalizer;
import java.util.Map;

@CrossOrigin
@RestController
public class ChatController {
    private static final Logger LOG = LoggerFactory.getLogger(ChatController.class);

    private final DemandeRepository demandeRepository;
    private final AnthropicChatModel chatModel;

    public ChatController(DemandeRepository demandeRepository, AnthropicChatModel chatModel) {
        this.demandeRepository = demandeRepository;
        this.chatModel = chatModel;
    }

    @GetMapping("/ai/generate")
    public Map generate(@RequestParam(value = "message") String message) {
        return Map.of("generation", this.chatModel.call(message));
    }

    @GetMapping("/ai/generate/time")
    public Map generateTime(@RequestParam(value = "message") String message) {
        String response = ChatClient.create(chatModel)
                .prompt(message)
                .tools(new DateTimeTools())
                .call()
                .content();
        return Map.of("generation", response);
    }

    @GetMapping("/ai/action")
    public Map generateAction(@RequestParam(value = "message") String message) {
        String response = ChatClient.create(chatModel)
                .prompt(message)
                .tools(new DateTimeTools())
                .call()
                .content();
        return Map.of("generation", response);
    }

    @GetMapping("/ai/demande")
    public Map generateDemande(@RequestParam(value = "message") String message) {
        String response = ChatClient.create(chatModel)
                .prompt(message)
                .tools(new DemandeTools())
                .toolContext(Map.of("demandeRepository", this.demandeRepository))
                .call()
                .content();
        return Map.of("generation", response);
    }

    @GetMapping(value = "/ai/demande-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateDemandeStream(@RequestParam(value = "message") String message) {

        return ChatClient.create(chatModel)
                .prompt(message)
                .tools(new DemandeTools())
                .toolContext(Map.of("demandeRepository", demandeRepository))
                .stream()
                .content();
    }
}
