import actions.Actions;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Card> deck = new ArrayList<>();
        ArrayList<Card> hand = new ArrayList<>();
        ArrayList<Card> discardPile = new ArrayList<>();

        String currentColor = "RED";

        // Example: Draw a card
        Card drawnCard = Actions.drawCard(deck, discardPile);

        if (drawnCard != null) {
            hand.add(drawnCard);
        }

        // Example: Display player's hand
        System.out.println("Your hand:");

        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i));
        }

        // Ask player which card to play
        System.out.println("Choose a card index to play:");

        int choice = scanner.nextInt();

        // Play selected card
        boolean played = Actions.playCard(
            hand,
            discardPile,
            choice,
            currentColor
        );

        if (played) {
            System.out.println("Card successfully played!");
        }

        scanner.close();
    }
}

