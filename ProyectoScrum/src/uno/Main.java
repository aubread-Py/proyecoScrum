package uno;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		try (Scanner s = new Scanner(System.in)) {
			
			String option = Menu.principal(s);

			switch (option) {
			case "play": 
				Integer playerNumber = Menu.getPlayerCount(s);
				break;
				
			default:
				throw new IllegalArgumentException("Unexpected value: " + option);
			}
		}
	}
}
