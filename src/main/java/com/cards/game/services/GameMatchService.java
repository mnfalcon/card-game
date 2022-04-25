package com.cards.game.services;

import com.cards.game.models.*;
import com.cards.game.models.enums.PlayerEnum;
import com.cards.game.models.enums.TargetType;
import com.cards.game.services.exceptions.InvalidAttackMoveException;
import com.cards.game.services.exceptions.InvalidSummonException;
import com.cards.game.services.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class GameMatchService extends BaseService<GameMatch> {

    @Autowired
    private CardInstanceService cardInstanceService;
    @Autowired
    private PlayerAttackMoveService playerAttackMoveService;
    @Autowired
    private PlayerSummonMoveService playerSummonCardMove;
    @Autowired
    private PlayerService playerService;

    public GameMatchService(JpaRepository<GameMatch, Long> repository) {
        super(repository);
    }

    public PlayerSummonCardMove processSummonCard(PlayerSummonCardMove summonCardMove) {

        if (!checkPlayerMatchValidity(summonCardMove) || !cardBelongsToGameMatchInstance(summonCardMove)) {
            throw new InvalidSummonException();
        }
        GameMatch gameMatch = repository.getById(summonCardMove.getGameMatchId());
        if (PlayerEnum.PLAYER1 == summonCardMove.getInstigator().getInstigatorType()) {
            gameMatch.getPlayer1Battlefield().add(summonCardMove.getAttackingCard());
        }
        else if (PlayerEnum.PLAYER2 == summonCardMove.getInstigator().getInstigatorType()) {
            gameMatch.getPlayer2Battlefield().add(summonCardMove.getAttackingCard());
        }
        repository.save(gameMatch);
        return playerSummonCardMove.save(summonCardMove);
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

    private PlayerAttackMove applyDamageToCard(PlayerAttackMove attackMove) {
        CardInstance cardInstance = cardInstanceService.findById(attackMove.getTargetId());
        cardInstance.setCurrentHealth(cardInstance.getCurrentHealth() - attackMove.getAttackingCard().getCurrentAttack());
        CardInstance attackingCard = attackMove.getAttackingCard();
        attackingCard.setCurrentHealth(attackingCard.getCurrentHealth() - cardInstance.getCurrentAttack());

        // check if dead and if dead then remove from battlefield and place into gameMatch graveyard
        if (cardInstance.getCurrentHealth() <= 0) {
            GameMatch gameMatch = repository.getById(attackMove.getGameMatchId());
            if (attackMove.getInstigator().getInstigatorType() == PlayerEnum.PLAYER1) {
                gameMatch.getPlayer2Battlefield().remove(cardInstance);
                gameMatch.getGraveyard().add(cardInstance);
            }
            if (attackMove.getInstigator().getInstigatorType() == PlayerEnum.PLAYER2) {
                gameMatch.getPlayer1Battlefield().remove(cardInstance);
                gameMatch.getGraveyard().add(cardInstance);
            }
            repository.save(gameMatch);
        }
        cardInstanceService.save(cardInstance);
        cardInstanceService.save(attackingCard);
        return attackMove;
    }

    private PlayerAttackMove applyDamageToPlayer(PlayerAttackMove attackMove) {
        Player player = playerService.findById(attackMove.getTargetId());
        player.setHealthPoints(player.getHealthPoints() - attackMove.getAttackingCard().getCurrentAttack());
        playerService.save(player);
        return attackMove;
    }

    private boolean isAttackMoveValid(PlayerAttackMove attackMove) {

        if (!checkPlayerMatchValidity(attackMove)) {
            return false;
        }
        if (!cardBelongsToGameMatchInstance(attackMove)) {
            return false;
        }
        GameMatch gameMatch = repository.getById(attackMove.getGameMatchId());
        PlayerEnum instigatorType = attackMove.getInstigator().getInstigatorType();
        // check for valid target
        if (attackMove.getTargetType() == TargetType.CARD) {
            if (instigatorType == PlayerEnum.PLAYER1) {
                return gameMatch.getPlayer1Battlefield().stream()
                        .anyMatch(c -> attackMove.getTargetId().equals(c.getId()));
            }
            else if (instigatorType == PlayerEnum.PLAYER2) {
                return gameMatch.getPlayer1Battlefield().stream()
                        .anyMatch(c -> attackMove.getTargetId().equals(c.getId()));
            }
        }

        if (attackMove.getTargetType() == TargetType.PLAYER) {
            return Objects.equals(gameMatch.getPlayer1().getId(), attackMove.getTargetId())
                    || Objects.equals(gameMatch.getPlayer2().getId(), attackMove.getTargetId());
        }

        return false;
    }

    /** Checks for gameMatchId existing, player belonging to the match and if player turn */
    private boolean checkPlayerMatchValidity(BasePlayerMove move) {
        // check for gameMatch id
        Optional<GameMatch> gameMatchOptional = repository.findById(move.getGameMatchId());
        if (!gameMatchOptional.isPresent()) {
            return false;
        }
        // check for instigator belonging to the gameMatch instance
        GameMatch gameMatch = gameMatchOptional.get();
        if (!gameMatch.getPlayer1().getId().equals(move.getInstigator().getId())
                || !gameMatch.getPlayer2().getId().equals(move.getInstigator().getId())) {
            return false;
        }
        //check for player turn
        PlayerEnum instigatorType = move.getInstigator().getInstigatorType();
        if (instigatorType != gameMatch.getCurrentTurn()) {
            return false;
        }
        return true;
    }

    private boolean cardBelongsToGameMatchInstance(BasePlayerMove move) {
        Player player = move.getInstigator();
        return player.getHand().stream().anyMatch(c -> move.getAttackingCard().getId().equals(c.getId()));
    }
}
