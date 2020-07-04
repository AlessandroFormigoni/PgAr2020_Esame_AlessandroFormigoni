package it.unibs.ing.arnaldo.unoghioh;
import java.util.*;

public class Game {
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Player> hallOfFame = new ArrayList<>();
	private Deck gameDeck;
	private Deck discarded = new Deck(0);
	
	/**
	 * Regulates the flow of the game and checks if someone has won
	 */
	public void play() {
		boolean hasFinished = false;
		for (Player player : players) {
			drawSeven(player);
		}
		discarded.addCard(gameDeck.getRandom());
		while(gameDeck.getDeckSize()>0 && !hasFinished) {
			for (Player player : players) {
				turn(player);
				if(player.getHand().size()==0) {
					wins(player);
					hasFinished = true;
					break;
				}
				if(gameDeck.getDeckSize()==0)
					gameDeck.setDeck(discarded.getDeck());
			}
		}
		for (Player player : players) {
			player.setRemainingCards(player.cardsInHand());
		}
		sort();
		for (Player player : players)
			System.out.println(player.getRemainingCards()+" "+player.getName());
		gameDeck = new Deck(gameDeck.getSize());
	}
	
	/**
	 * Shows the player the action he can and cannot perform during his turn
	 * @param p: The current turn player
	 */
	public void turn(Player p) {
		System.out.println("---------------------------------");
		System.out.println("E' il tuo turno, "+p.getName()+", "+" ti restano "+p.cardsInHand()+" carte");
		if(canPut(p)) {
		place(p);
		}
		else {
			System.out.println("Non puoi scartare questo turno, devi pescare");
			p.drawToHand(gameDeck.draw());
			if(canPut(p))
				place(p);
			else
				System.out.println("Mi dispiace, ma in questo turno non puoi scartare");
		}
	}
	
	/**
	 * Place a card on top of the discarded deck, if it is allowed
	 * @param p: Current player
	 */
	private void place(Player p) {
		boolean canPerform;
		do {
			System.out.println("L' ultima carta scartata e' : "+discarded.getDeck().peek().toString());
			Card c = p.selectCard();
			if (discarded.getDeckSize()>0 && isLegal(c)) {
				p.removeFromHand(c);
				discarded.addCard(c);
				canPerform = false;
			}
			else {
				System.out.println("Non puoi scartare questa carta");
				canPerform = true;
			}
		} while (canPerform);
	}
	
	/**
	 * Checks if the values of the last discarded card and the just thrown card match
	 * @param valueA
	 * @param valueB
	 * @return
	 */
	public boolean valueCheck(int valueA, int valueB) {
		return valueA==valueB;
	}
	/**
	 * Checks if the color of the last discarded card and the just thrown card match
	 * @param color
	 * @param cardColor
	 * @return
	 */
	public boolean currentColor(Seed color, Seed cardColor) {
		return color.equals(cardColor);
	}
	
	/**
	 * For whom the victory called
	 * @param p The winner
	 */
	public void wins(Player p) {
		System.out.println("Congratulazioni "+p.getName()+ " hai vinto!");
		p.setScore(100);
		hallOfFame.add(p);
	}
	
	/**
	 * A "better" bubblesort for sorting players by remaining cards
	 * @see computerphile
	 */
	public void sort() {
		Player tempPl;
		for(int i=0;i<players.size()-1;i++) {
			for (int j=1;j<players.size()-i;j++) {
				if(players.get(j-1).getRemainingCards() > players.get(j).getRemainingCards()) {
					tempPl = players.get(j-1);
					players.set(j-1,players.get(j));
					players.set(j,tempPl);
				}
			}
		}
	}
	
	/**
	 * Initial card draw
	 * @param p Player
	 */
	public void drawSeven(Player p) {
		for(int i=0;i<7;i++)
			p.drawToHand(gameDeck.draw());
	}
	
	/**
	 * Checks if the player has any card to discard, else he won't be allowed 
	 * to discard
	 * @param p Player
	 * @return
	 */
	public boolean canPut(Player p) {
		for (Card card : p.getHand()) {
			if(isLegal(card))
				return true;
		}
		return false;
	}

	/**
	 * A combination of previous methods to render the code more readable above
	 * @param c
	 * @return
	 */
	public boolean isLegal(Card c) {
		return (valueCheck(c.getValue(),discarded.getDeck().peek().getValue()) || currentColor(c.getColor(), discarded.getDeck().peek().getColor()));
	}
	
	//get-set zone
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Deck getGameDeck() {
		return gameDeck;
	}

	public Deck getDiscarded() {
		return discarded;
	}
	
	public ArrayList<Player> getHallOfFame() {
		return hallOfFame;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public void setGameDeck(Deck gameDeck) {
		this.gameDeck = gameDeck;
	}

	public void setDiscarded(Deck discarded) {
		this.discarded = discarded;
	}
	
}
