package card;

public class Card {
	
	private String color;
	private Value value;

	public Card(String color, Value value) {
		this.color = color;
		this.value = value;
	}
	
	public Object getColor() {
		return color;
	}

	public String getValue() {
		return value.name();
	}

}
