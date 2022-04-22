package com.cards.game.controllers;

import com.cards.game.models.Message;
import com.cards.game.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @MessageMapping("/")
    @SendTo("/topic/message")
    public ResponseEntity saveMessage(@RequestBody Message message) {
        return ResponseEntity.ok(messageService.save(message));
    }
}
