package card;

public class Card {
	
	private String color;
	private Value value;

	public Card(String color, String value) {
		this.color = color;
		this.value = Value.valueOf(value);
	}
	
	public Object getColor() {
		return color;
	}

	public String getValue() {
		return value.name();
	}

    @Override
    public String toString() {
        return "[" + color + " - " + value + "]";
    }
}
