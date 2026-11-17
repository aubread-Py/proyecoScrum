package uno;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Player;

public class Menus {
	
	public static String principal(Scanner s) {
		
		final List<String> options = new ArrayList<>();
		options.add("play");
		options.add("history");
		options.add("exit");
		
		String input;
		Boolean valid = false;

		do {
			
			System.out.println("options:");
			System.out.println("play");
			System.out.println("history");
			System.out.println("exit");

			input = s.nextLine();
			valid = options.contains(input);

		} while (!valid);
		
		return input;
	}
	
	public static Integer getPlayerCount(Scanner s) {
		
		Integer input;
		Boolean valid = false;
		
		do {
			
			System.out.print("\nnumber of players (2/3): ");
			try {
				input = Integer.parseInt(s.nextLine());
			} catch (NumberFormatException ignored) {
				input = -1;
			}
			
			valid = input == 2 || input == 3;
				
		} while (!valid);
		
		return input;
	}

	public static List<Player> createPlayers(int playerNumber, Scanner s) {
		
		List<Player> players = new ArrayList<>();
		int i = 1;

		while (i <= playerNumber) {
			
			System.out.print("player " + i + " name: ");
			String name = s.nextLine();

			if (name.isBlank()) {
				System.out.println("player name can not be blank");
			} else {
				players.add(new Player(name));
				i++;	
			}
		}

		return players;
	}
}
