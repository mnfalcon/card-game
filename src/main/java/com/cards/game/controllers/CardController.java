package com.cards.game.controllers;

import com.cards.game.models.Card;
import com.cards.game.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/")
    public ResponseEntity save(@RequestBody Card card) {
        Card newCard = new Card(card);
        return ResponseEntity.ok(cardService.save(newCard));
    }
}
