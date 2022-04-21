package com.cards.game.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameMatch implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Player player1;
    @OneToOne
    private Player player2;
    private boolean isPlayer1Turn;
    private LocalDateTime turnStart;
    private LocalDateTime turnEnd;
    @OneToMany
    private List<Card> graveyard;
    @OneToMany
    private List<Card> player1Battlefield;
    @OneToMany
    private List<Card> player2Battlefield;
    @OneToOne
    private GameChat gameChat;
    @OneToMany
    private List<PlayerSummonCardMove> playerMoves;

}
