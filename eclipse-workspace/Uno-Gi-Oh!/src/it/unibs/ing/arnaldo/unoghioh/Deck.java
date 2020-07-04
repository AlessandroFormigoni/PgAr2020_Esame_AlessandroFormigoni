package it.unibs.ing.arnaldo.unoghioh;
import java.util.*;
import it.unibs.fp.mylib.*;

public class Deck {
	private Stack<Card> deck = new Stack<>();
	private int size;
	
	public Deck(int size) {
		this.size = size;
		generateDeck();
		shuffle();
	}
	
	/**
	 * Generates a new deck of size cards, and tries to give a proportional 
	 * amount of cards based on the total size. This makes odd sized inputs
	 * a bit unfair
	 */
	private void generateDeck() {
		for (Seed seed : Seed.values()) {
			int initialValue = size/(Seed.values().length*10);
			for(int i=initialValue;i<size/Seed.values().length+initialValue;i++) {
				if((int)Math.floor(i/initialValue-1)>9) {
					Card newCard = new Card(EstrazioniCasuali.estraiIntero(0, 9), seed);
					deck.push(newCard);
				}
				else {
					Card newCard = new Card((int)Math.floor(i/initialValue-1),seed);
					deck.push(newCard);
				}
			}
		}
	}
	
	/**
	 * A very brutal method for shuffling cards. Might be useful to replace
	 * this method with a less resource consuming one
	 */
	public void shuffle() {
		for(int i=0;i<deck.size();i++) {
			int randValA = EstrazioniCasuali.estraiIntero(0, deck.size()-1);
			int randValB = EstrazioniCasuali.estraiIntero(0, deck.size()-1);
			Card randCardA = deck.get(randValA);
			Card randCardB = deck.get(randValB);
			deck.set(randValB, randCardA);
			deck.set(randValA, randCardB);
		}
	}
	
	/**
	 * Draws the next card in the deck
	 * @return A card
	 */
	public Card draw() {
		return deck.pop();
	}
	
	//get-set zone
	
	public void addCard(Card c) {
		deck.push(c);
	}
	
	public Stack<Card> getDeck() {
		return deck;
	}

	public int getSize() {
		return size;
	}
	
	public int getDeckSize() {
		return deck.size();
	}
	
	public Card getRandom() {
		Card c = deck.get(EstrazioniCasuali.estraiIntero(0, deck.size()-2));
		deck.remove(c);
		return c;
	}

	public void setDeck(Stack<Card> deck) {
		this.deck = deck;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String toString() {
		return "Ecco il mazzo completo: \n" + deck;
	}
}
