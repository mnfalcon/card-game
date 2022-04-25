package com.cards.game.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerSummonCardMove implements BaseEntity, BasePlayerMove{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Player instigator;
    @OneToOne
    private CardInstance attackingCard;
    private Long gameMatchId;
}
