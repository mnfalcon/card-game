package com.cards.game.controllers;

import com.cards.game.models.Card;
import com.cards.game.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(cardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(cardService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity getByPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                    @RequestParam(name = "limit", defaultValue = "100") Integer limit) {
        return ResponseEntity.ok(cardService.getByPage(page, limit));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Card card, @PathVariable("id") Long id) {
        return ResponseEntity.ok(cardService.update(card, id));
    }
}
