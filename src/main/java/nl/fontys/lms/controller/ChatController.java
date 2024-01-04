package nl.fontys.lms.controller;

import nl.fontys.lms.domain.chatmessage.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {

    @MessageMapping("/app/chat.sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatMessage message) {
        return message;
    }
}

