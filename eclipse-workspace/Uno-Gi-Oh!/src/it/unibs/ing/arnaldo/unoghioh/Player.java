package it.unibs.ing.arnaldo.unoghioh;

import java.util.*;

import it.unibs.fp.mylib.InputDati;

public class Player {
	private String name;
	private ArrayList<Card> hand = new ArrayList<>();
	private int remainingCards=0;
	private int score = 0;
	private int victories = 0;
	
	public Player(String nome) {
		this.name = nome;
	}
	
	/**
	 * Selects card from your hand. Didn't use a foreach loop to show card position
	 * in the arraylist.
	 * @return The card that the user selects
	 */
	public Card selectCard() {
		for (int i=0;i<hand.size();i++) {
			System.out.println(i+". "+hand.get(i).toString());
		}
		int n = InputDati.leggiIntero("Quale carta scarterai?",0,hand.size()-1);
		return hand.get(n);
	}
	
	public int cardsInHand() {
		return hand.size();
	}
	
	public int getRemainingCards() {
		return remainingCards;
	}
	
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int newScore) {
		score += newScore;
		victories++;
	}
	
	public String getName() {
		return name;
	}
	public ArrayList<Card> getHand() {
		return hand;
	}
	public void setName(String nome) {
		this.name = nome;
	}
	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	public void drawToHand(Card card) {
		hand.add(card);
	}
	public void removeFromHand(Card card) {
		hand.remove(card);
	}
	
	public void setRemainingCards(int n) {
		this.remainingCards = n;
	}
	
	public int getVictories() {
		return victories;
	}

	public void setVictories(int victories) {
		this.victories = victories;
	}

	public String toString() {
		return "Giocatore " + name +" con punteggio " + score+"\n";
	}

}
