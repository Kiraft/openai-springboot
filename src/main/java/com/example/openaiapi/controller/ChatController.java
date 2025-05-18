package com.example.openaiapi.controller;

import com.example.openaiapi.services.ChatServices;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private ChatServices chatServices;

    @GetMapping("/api/v1/chat")
    public String chat(@RequestParam(value="prompt") String prompt) {
        return chatServices.generateResult(prompt);
    }
}
