package card;

public class Card {
	
	private Color color;
	private Value value;

	public Card(Color color, Value value) {
		this.color = color;
		this.value = value;
	}
	
	public Object getColor() {
		return color;
	}

	public Object getValue() {
		return value;
	}

}
