package uno;

import java.util.Scanner;

import menus.Menu;

public class Main {

	public static void main(String[] args) {

		try (Scanner s = new Scanner(System.in)) {
			
			String option = Menu.principal(s);

			switch (option) {
			
			case "play": 
				Integer playerNumber = Menu.getPlayerCount(s);
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
