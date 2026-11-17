package uno;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import actions.Actions;
import card.Card;
import classes.Player;
import classes.UnoDeck;

public class Uno {

    private ArrayList<Player> players;
    private UnoDeck deck;
    private ArrayList<Card> discardPile;
    private Scanner s;

    public Uno(ArrayList<Player> players, Scanner s) {

        this.players = players;
        deck = new UnoDeck();
        this.s = s;

        // the official UNO hand size is 7
        final int NUMBER_OF_CARDS = 7;
        deck.dealCards(players, NUMBER_OF_CARDS);

        discardPile = new ArrayList<>();
        discardPile.add(deck.drawCard());

        startGame();
    }

    private void startGame() {

        Player winner = null;
        int turnNumber = 0;

        while (winner == null) {

            // get player by turn
            Player currentPlayer = players.get(turnNumber % players.size());
            System.out.println("\n" + currentPlayer.getName() + "'s turn");
            currentPlayer.showHand();

            System.out.println("card on stack: " + discardPile.getLast());

            String input = Menus.playTurn(s);

            switch (input) {

                case "play":

                    System.out.print("You play (index): ");
                    Integer index = Integer.parseInt(s.nextLine());

                    if (Actions.playCard(
                            (ArrayList<Card>) currentPlayer.getHand(),
                            discardPile, index,
                            discardPile.getLast().getColor())) {

                        System.out.println("Played a card!");

                    } else {
                        continue;
                    }
                    break;

                case "draw":

                    break;

                default:
                    break;
            }

            turnNumber++;
        }
    }
}
