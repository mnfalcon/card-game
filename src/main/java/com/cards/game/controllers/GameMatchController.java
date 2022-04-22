package com.cards.game.controllers;

import com.cards.game.models.PlayerAttackMove;
import com.cards.game.services.GameMatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameMatchController {

    private GameMatchService gameMatchService;

    public ResponseEntity processAttackMove( @RequestBody PlayerAttackMove attackMove) {

        return ResponseEntity.ok(gameMatchService.processAttackMove(attackMove));
    }
}
