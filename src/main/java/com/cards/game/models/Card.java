package com.cards.game.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullFields;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card implements BaseCard, BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int attackPoints;
    @Column(nullable = false)
    private int healthPoints;
    private int manaCost;

    public Card(Card card) {
        this.id = card.id;
        this.name = card.name;
        this.description = card.description;
        this.attackPoints = card.attackPoints;
        this.healthPoints = card.healthPoints;
        this.manaCost = (card.attackPoints + card.healthPoints) / 2;
    }

}
