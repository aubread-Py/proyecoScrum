package uno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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

            switch (Menus.playTurn(s)) {

                case "play":

                    System.out.print("You play (index): ");
                    Integer cardIndex = Integer.parseInt(s.nextLine());

                    boolean successPlay = Actions.playCard(
                            (ArrayList<Card>) currentPlayer.getHand(),
                            discardPile,
                            cardIndex,
                            discardPile.getLast().getColor());

                    if (successPlay) {
                        actByValue(discardPile.getLast().getValue(), currentPlayer);
                        System.out.println("Played a card!");
                    } else {
                        // skip turn counter;
                        continue;
                    }

                    break;

                case "draw":

                    Card drawn = Actions.drawCard((ArrayList<Card>) deck.getCards(), discardPile);
                    currentPlayer.addCard(drawn);
                    break;

                default:
                    break;
            }

            // check if the player has won
            if (currentPlayer.getHand().size() == 1) {
                winner = currentPlayer;
            }

            turnNumber++;
        }
    
        // print winner
        System.out.println("The winner is " + winner.getName());
    }

    // capped at 3 players, just swap the other 2
    private void reversePlayerTurn(Player pivot) {

        int pivotIndex = players.indexOf(pivot);
        int prev = (pivotIndex + 2) % players.size();
        int next = (pivotIndex + 1) % players.size();

        Collections.swap(players, prev, next);
    }

    private void playerDrawN(Player player, int n) {
        for (int i = 0; i < n; i++) {
            player.addCard(deck.drawCard());
        }
    }

    private Player nextPlayer(Player player) {
        int next = (players.indexOf(player) + 1) % players.size();
        return players.get(next);
    }

    private void actByValue(String value, Player currentPlayer) {

        switch (discardPile.getLast().getValue()) {

            case "SKIP":
                Player move = players.remove(0);
                players.add(move);
                break;

            case "REVERSE":
                reversePlayerTurn(currentPlayer);
                break;

            case "DRAW_TWO":
                playerDrawN(nextPlayer(currentPlayer), 2);
                break;

            case "WILD":
                String color = Menus.getColor(s);
                discardPile.getLast().setColor(color);
                break;

            case "WILD_DRAW_FOUR":
                color = Menus.getColor(s);
                discardPile.getLast().setColor(color);
                playerDrawN(nextPlayer(currentPlayer), 4);
                break;

            default:
                break;
        }
    
    }

}
