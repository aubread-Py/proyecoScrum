package uno;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import classes.Player;
import record.Record;

public class Menus {

	public static String principal(Scanner s) {

		final List<String> options = new ArrayList<>();
		options.add("play");
		options.add("history");
		options.add("exit");
		return handleMenu(options, s);
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

	public static ArrayList<Player> createPlayers(int playerNumber, Scanner s) {

		ArrayList<Player> players = new ArrayList<>();
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

	public static String playTurn(Scanner s) {

		final List<String> options = new ArrayList<>();
		options.add("play");
		options.add("draw");
		return handleMenu(options, s);
	}

	public static String getColor(Scanner s) {

		final List<String> options = new ArrayList<>();
		options.add("RED");
		options.add("BLUE");
		options.add("GREEN");
		options.add("YELLOW");

		return handleMenu(options, s);
	}

	// print a menu from a list and get a valid input
	private static String handleMenu(List<String> options, Scanner s) {

		String input;

		do {
			System.out.print("\nOptions: " + options + ": ");
			input = s.nextLine();
		} while (!options.contains(input));

		return input;
	}

	public static void showHistory(Scanner s) {

		try {

			for (Path history : Record.getRecords()) {
				
				BufferedReader reader = new BufferedReader(new FileReader(history.toFile()));

				System.out.println();
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}

				reader.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
