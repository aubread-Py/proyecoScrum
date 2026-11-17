package uno;

import java.util.List;
import java.util.Scanner;

import classes.Player;

public class Main {

	public static void main(String[] args) {

		try (Scanner s = new Scanner(System.in)) {
			
			String option = Menus.principal(s);

			switch (option) {
			
			case "play": 
				Integer playerNumber = Menus.getPlayerCount(s);
				List<Player> players = Menus.createPlayers(playerNumber, s);
				new Uno(players, s);
				break;
			
			case "history":
				break;

			case "exit":
				System.out.println("quiting...");
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + option);
			}
		}
	}
}
