package actions;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import card.Card;

public class Actions {

    private static Random random = new Random();

    // DRAW A CARD
    public static Card drawCard(ArrayList<Card> deck,
                                ArrayList<Card> discardPile) {

        // If deck is empty, reshuffle the discard pile
        if (deck.isEmpty()) {
            if (discardPile.size() <= 1) {
                System.out.println("No cards left to draw!");
                return null;
            }

            Card topCard = discardPile.remove(discardPile.size() - 1);

            deck.addAll(discardPile);
            discardPile.clear();
            discardPile.add(topCard);
        }

        // Select a random card
        int randomIndex = random.nextInt(deck.size());

        Card drawnCard = deck.remove(randomIndex);

        System.out.println("You drew: " + drawnCard);

        return drawnCard;
    }


    // PLAY A CARD
    public static boolean playCard(ArrayList<Card> hand,
                                   ArrayList<Card> discardPile,
                                   int cardIndex,
                                   String currentColor) {

        if (cardIndex < 0 || cardIndex >= hand.size()) {
            System.out.println("Invalid card selection!");
            return false;
        }

        Card selectedCard = hand.get(cardIndex);

        // Check if card can be played
        boolean valid =
                selectedCard.getColor().equals("WILD") ||
                selectedCard.getColor().equals(currentColor) ||
                selectedCard.getValue().equals(
                    discardPile.get(discardPile.size() - 1).getValue()
                );

        // Check Wild Draw Four legality
        if (selectedCard.getValue().equals("WILD_DRAW_FOUR")) {
            valid = true;

            for (Card card : hand) {
                if (card.getColor().equals(currentColor)) {
                    valid = false;
                    break;
                }
            }
        }

        if (!valid) {
            System.out.println("You cannot play this card!");
            return false;
        }

        // Remove card from hand and add to discard pile
        hand.remove(cardIndex);
        discardPile.add(selectedCard);

        System.out.println("Card played: " + selectedCard);

        return true;
    }
}

