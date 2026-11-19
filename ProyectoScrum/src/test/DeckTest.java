package test;

import card.Card;
import classes.UnoDeck;

public class DeckTest {
    public static void main(String[] args) {
        UnoDeck deck = new UnoDeck();
        System.out.println(deck.getCards());

        System.out.println(new Card("RED", "1"));
    }
}
