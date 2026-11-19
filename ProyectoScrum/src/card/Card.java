package card;

public class Card {
	
	private String color;
	private String value;

	public Card(String color, String value) {
		this.color = color;
		this.value = value;
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getValue() {
		return value;
	}

    @Override
    public String toString() {

		String ansiColor = "";

		switch (getColor()) {
			case "RED": color = "\033[31mRED\033[0m"; break;
			case "BLUE": color = "\033[34mBLUE\033[0m"; break;
			case "GREEN": color = "\033[32mGREEN\033[0m"; break;
			case "YELLOW": color = "\033[33mYELLOW\033[0m"; break;
			default: break;
		}

        return ansiColor + this.color + " - " + value + "\033[37m";
    }
}
