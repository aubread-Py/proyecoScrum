package classes;

import card.Card;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Card> hand;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card removeCard(int index) {
        if (index >= 0 && index < hand.size()) {
            return hand.remove(index);
        }

        return null;
    }

    public void showHand() {
        System.out.println("Cards of " + name + ":");
        int i = 0;
        for (Card card : hand) {
            System.out.println("[" + i + "] " + card);
            i++;
        }
    }
}
