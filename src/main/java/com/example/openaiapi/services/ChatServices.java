

package com.example.openaiapi.services;

import org.springframework.ai.chat.messages.*;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServices {

    @Autowired
    private OpenAiChatModel chatModel;

    public String generateResult(String userReference) {
        List<Message> messages = List.of(
                new SystemMessage("Eres un experto en literatura que recomienda libros según los gustos del usuario. Siempre responde en español. Sé amigable. Devuelve solo 3 libros como máximo. Por cada libro, menciona solo el título y una pequeña reseña de máximo 30 palabras. No incluyas texto adicional fuera de las recomendaciones."),
                new UserMessage("¿Cuáles son sus métodos de pago?"),
                new AssistantMessage("Aceptamos tarjeta, transferencia bancaria y PayPal."),
                new UserMessage("¿Hacen envíos a todo el país?"),
                new AssistantMessage("Sí, realizamos envíos a cualquier parte del país dentro de 3 a 5 días hábiles."),
                new UserMessage("¿Dónde están ubicados?"),
                new AssistantMessage("Nuestra tienda física está en Ciudad de México, pero puedes comprar desde nuestra web."),
                new UserMessage(userReference)
        );

        ChatResponse response = chatModel.call(new Prompt(messages));

        if (response != null) {
            return response.getResult().getOutput().getText();
        }
        return "Error: No hay respuesta del modelo OpenAI.";
    }
}
