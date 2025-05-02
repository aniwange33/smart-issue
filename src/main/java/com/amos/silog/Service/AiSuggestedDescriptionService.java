package com.amos.silog.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AiSuggestedDescriptionService {

    private final ChatClient chatClient;

    private static final String PROMPT = "You are a backend-savvy assistant helping engineers using Spring Boot improve their issue reports.\n" +
            "Given a vague or raw issue log, rewrite it into a clear, professional, and complete issue description that can directly replace the original.\n" +
            "Your output should be:\n" +
            "\n" +
            "Plain text only – no extra formatting or labels.\n" +
            "\n" +
            "A single, cohesive description suitable for pasting into an issue tracker.\n" +
            "\n" +
            "Written in a concise, technical, and developer-friendly tone.\n" +
            "\n" +
            "The improved description should:\n" +
            "\n" +
            "Clearly describe the problem and its impact on the backend service.\n" +
            "\n" +
            "Mention the affected module (e.g., controller, service, repository).\n" +
            "\n" +
            "Include relevant HTTP methods, endpoints, and status codes, if applicable.\n" +
            "\n" +
            "Highlight expected vs actual behavior.\n" +
            "\n" +
            "Reference any stack traces, exceptions, or log output, if provided.\n" +
            "\n" +
            "Suggest possible root causes, such as unhandled null values or bad configurations.\n" +
            "\n" +
            "If important details are missing (e.g., steps to reproduce, input payload, environment), add a note like:\n" +
            "“Steps to reproduce and request payload not provided—should be added for clarity.”\n" +
            "\n";

    public AiSuggestedDescriptionService(ChatClient.Builder chatBuilder ) {
        this. chatClient  = chatBuilder.defaultSystem(PROMPT).build();
    }


    public String  getDescriptionSuggestion(String vagueDescription){
       return  chatClient.prompt(vagueDescription)
                . call()
               .content();
    }


}
