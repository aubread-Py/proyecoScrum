package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import card.Card;

public class UnoDeck {

    private List<Card> cards;

    public UnoDeck() {
        cards = new ArrayList<>();
        createDeck();
        shuffleDeck();
    }

    private void createDeck() {

        String[] colors = {
            "RED", "BLUE", "GREEN", "YELLOW"
        };

        for (String color : colors) {

            // One zero card per color
            cards.add(new Card(color, "0"));

            // Two cards of each number from 1 to 9
            for (int number = 1; number <= 9; number++) {

                cards.add(new Card(color, String.valueOf(number)));
                cards.add(new Card(color, String.valueOf(number)));
            }

            // Two special cards of each type
            String[] specialCards = {
                "SKIP", "REVERSE", "DRAW_TWO"
            };

            for (String special : specialCards) {

                cards.add(new Card(color, special));
                cards.add(new Card(color, special));
            }
        }

        // Four wild cards and four wild draw four cards
        for (int i = 0; i < 4; i++) {

            cards.add(new Card("WILD", "WILD"));
            cards.add(new Card("WILD", "WILD_DRAW_FOUR"));
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {

        if (cards.isEmpty()) {
            System.out.println("The deck is empty.");
            return null;
        }

        return cards.remove(0);
    }

    // Repart cards between players
    public void dealCards(List<Player> players, int numberOfCards) {

        for (Player player : players) {

            for (int i = 0; i < numberOfCards; i++) {

                Card card = drawCard();

                if (card != null) {
                    player.addCard(card);
                } else {
                    return;
                }
            }
        }
    }

    public int getRemainingCards() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
