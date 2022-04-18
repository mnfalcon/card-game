package com.cards.game.controllers;

import com.cards.game.models.SpellCard;
import com.cards.game.models.User;
import com.cards.game.services.SpellCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class SpellCardController {

    @Autowired
    private SpellCardService spellCardService;

    @GetMapping("/all")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(spellCardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(spellCardService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity getByPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                    @RequestParam(name = "limit", defaultValue = "100") Integer limit) {
        return ResponseEntity.ok(spellCardService.getByPage(page, limit));
    }

    @PostMapping("/")
    public ResponseEntity save(@RequestBody SpellCard card) {
        return ResponseEntity.ok(spellCardService.save(card));
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody SpellCard card, Long id) {
        return ResponseEntity.ok(spellCardService.update(card, id));
    }
}
