package com.cards.game.models;

import com.cards.game.models.enums.PlayerEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    private int healthPoints;
    private int manaPoints;
    @ManyToMany
    private List<CardInstance> deck;
    @ManyToMany
    private List<CardInstance> hand;
    private int cardsInHand;
    private PlayerEnum instigatorType;

}
