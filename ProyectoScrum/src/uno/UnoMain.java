package uno;
import java.util.Scanner;

public class UnoMain {
	public static void main(String[] args) {
		Scanner ask = new Scanner(System.in);
		String accion;
		System.out.println("Enter one of the next actions: draw or play");
		do {
		accion = ask.next();
			switch (accion) {
			case "draw": {	
				break;
			}
			case "play": {
				break;
			}
			default:
				accion = "null";
				System.out.println("The action must have the exact same spelling as in the menu");
				break;
			}
		} while (accion.equals("null"));
		ask.close();
	}
}