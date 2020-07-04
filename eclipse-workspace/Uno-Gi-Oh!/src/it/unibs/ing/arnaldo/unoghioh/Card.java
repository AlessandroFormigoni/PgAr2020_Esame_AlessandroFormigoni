package it.unibs.ing.arnaldo.unoghioh;

public class Card {
	private int value;
	private String specialValue;
	private Seed color;
	private int id;
	private static int counter = 0;
	
	public Card(int value, Seed color) {
		this.value = value;
		this.color = color;
		id = counter;
		counter++;
	}
	
	public Card(String specialValue, Seed color) {
		this.specialValue = specialValue;
		this.color = color;
	}

	public int getValue() {
		return value;
	}

	public Seed getColor() {
		return color;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setColor(Seed color) {
		this.color = color;
	}
	
	public String toString() {
		if(specialValue==null)
			return value + " colore " + color + "\n";
		else
			return specialValue + " colore " + color + "\n";
	}
}
