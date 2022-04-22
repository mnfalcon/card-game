package com.cards.game.services;

import com.cards.game.models.CardInstance;
import com.cards.game.models.GameMatch;
import com.cards.game.models.Player;
import com.cards.game.models.PlayerAttackMove;
import com.cards.game.models.enums.Instigator;
import com.cards.game.models.enums.TargetType;
import com.cards.game.services.exceptions.InvalidAttackMoveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameMatchService extends BaseService<GameMatch> {

    @Autowired
    private CardInstanceService cardInstanceService;
    @Autowired
    private PlayerAttackMoveService playerAttackMoveService;
    @Autowired
    private PlayerService playerService;

    public GameMatchService(JpaRepository<GameMatch, Long> repository) {
        super(repository);
    }

    public PlayerAttackMove processAttackMove(PlayerAttackMove attackMove) {
        if(isAttackMoveValid(attackMove)) {
            if (attackMove.getTargetType() == TargetType.CARD) {
                return playerAttackMoveService.save(applyDamageToCard(attackMove));
            }
            if (attackMove.getTargetType() == TargetType.PLAYER) {
                return playerAttackMoveService.save(applyDamageToPlayer(attackMove));
            }
        }
        throw new InvalidAttackMoveException();
    }

    public PlayerAttackMove applyDamageToCard(PlayerAttackMove attackMove) {
        CardInstance cardInstance = cardInstanceService.findById(attackMove.getTargetId());
        cardInstance.setCurrentHealth(cardInstance.getCurrentHealth() - attackMove.getAttackingCard().getCurrentAttack());
        CardInstance attackingCard = attackMove.getAttackingCard();
        attackingCard.setCurrentHealth(attackingCard.getCurrentHealth() - cardInstance.getCurrentAttack());

        // check if dead and if dead then remove from battlefield and place into gameMatch graveyard
        if (cardInstance.getCurrentHealth() <= 0) {
            GameMatch gameMatch = repository.getById(attackMove.getGameMatchId());
            if (attackMove.getInstigator().getInstigatorType() == Instigator.PLAYER1) {
                gameMatch.getPlayer2Battlefield().remove(cardInstance);
                gameMatch.getGraveyard().add(cardInstance);
            }
            if (attackMove.getInstigator().getInstigatorType() == Instigator.PLAYER2) {
                gameMatch.getPlayer1Battlefield().remove(cardInstance);
                gameMatch.getGraveyard().add(cardInstance);
            }
        }
        cardInstanceService.save(cardInstance);
        cardInstanceService.save(attackingCard);
        return attackMove;
    }

    public PlayerAttackMove applyDamageToPlayer(PlayerAttackMove attackMove) {
        Player player = playerService.findById(attackMove.getTargetId());
        player.setHealthPoints(player.getHealthPoints() - attackMove.getAttackingCard().getCurrentAttack());
        playerService.save(player);
        return attackMove;
    }

    public boolean isAttackMoveValid(PlayerAttackMove attackMove) {

        // check for gameMatch id
        Optional<GameMatch> gameMatchOptional = repository.findById(attackMove.getGameMatchId());
        if (!gameMatchOptional.isPresent()) {
            return false;
        }
        // check for instigator belonging to the gameMatch instance
        GameMatch gameMatch = gameMatchOptional.get();
        if (!gameMatch.getPlayer1().getId().equals(attackMove.getInstigator().getId())
            || !gameMatch.getPlayer2().getId().equals(attackMove.getInstigator().getId())) {
            return false;
        }
        // check for valid target
        if (attackMove.getTargetType() == TargetType.CARD) {
            if (attackMove.getInstigator().getInstigatorType() == Instigator.PLAYER1) {
                return gameMatch.getPlayer1Battlefield().stream()
                        .anyMatch(c -> attackMove.getTargetId().equals(c.getId()));
            }
            else if (attackMove.getInstigator().getInstigatorType() == Instigator.PLAYER2) {
                return gameMatch.getPlayer1Battlefield().stream()
                        .anyMatch(c -> attackMove.getTargetId().equals(c.getId()));
            }
        }

        if (attackMove.getTargetType() == TargetType.PLAYER) {
            if (gameMatch.getPlayer1().getId() == attackMove.getTargetId()) {
                return true;
            }
        }

        return false;
    }
}
