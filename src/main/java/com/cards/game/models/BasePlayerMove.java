package com.cards.game.models;

public interface BasePlayerMove {
    public CardInstance getAttackingCard();
    public Long getGameMatchId();
    public Player getInstigator();
}
