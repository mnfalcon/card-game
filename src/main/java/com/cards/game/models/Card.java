package com.cards.game.models;

import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullFields;

import javax.persistence.*;

@Entity
@Data
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
    private String imageUrl;
    @ManyToOne
    private User author;

    public Card(Card card) {
        this.id = card.id;
        this.name = card.name;
        this.description = card.description;
        this.attackPoints = card.attackPoints;
        this.healthPoints = card.healthPoints;
        this.manaCost = (card.attackPoints + card.healthPoints) / 2;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", attackPoints=" + attackPoints +
                ", healthPoints=" + healthPoints +
                ", manaCost=" + manaCost +
                ", imageUrl='" + imageUrl + '\'' +
                ", author=" + author +
                '}';
    }
}
