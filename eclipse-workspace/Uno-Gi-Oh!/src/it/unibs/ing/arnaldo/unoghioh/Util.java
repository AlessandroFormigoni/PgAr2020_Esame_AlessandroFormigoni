package it.unibs.ing.arnaldo.unoghioh;

import java.util.*;
import it.unibs.fp.mylib.InputDati;

public class Util {

	/**
	 * Creates a new Player
	 * @return a player
	 */
	public static Player createPlayer() {
		System.out.println("Crea un nuovo giocatore");
		String name = InputDati.leggiStringaNonVuota("Inserire il nome: ");
		return new Player(name);
	}
	
	/**
	 * Sets the stages for the game Uno-Gi-Oh
	 * @return Game
	 */
	public static Game initialize() {
		Game game = new Game();
		ArrayList<Player> players = new ArrayList<>();
		System.out.println("Benvenuti a Uno-Gi-Oh!");
		int numberOfPlayers = InputDati.leggiIntero("Inserire il numero dei giocatori(2-4)",2,4);
		for(int i=0;i<numberOfPlayers;i++) {
			players.add(createPlayer());
		}
		game.setPlayers(players);
		int deckSize = InputDati.leggiInteroConMinimo("Inserisci le dimensioni del mazzo (min. 80 carte)", 80);
		game.setGameDeck(new Deck(deckSize));
		return game;		
	}
	
	/**
	 * The menu, a very simple user interface
	 * @param game Operates over game data and methods
	 */
	public static void gameMenu(Game game) {
		boolean loop = true;
		do {
			System.out.println("-----------------------------------------------------------------");
			System.out.println("1. Inizia una nuova partita");
			System.out.println("2. Visualizza punteggi");
			System.out.println("3. Salva i risulati delle partite (sovrascrive i dati precedenti)");
			System.out.println("4. Visualizza i risultati delle partite salvate");
			System.out.println("5. Visualizza i mazzi importati");
			System.out.println("6. Scegli il mazzo con cui giocare");
			System.out.println("7. Termina il programma");
			int s = InputDati.leggiIntero(">");
			switch(s) {
			case 1:
				game.play();
				break;
			case 2:
				System.out.println(game.getPlayers().toString());
				break;
			case 3:
				WriteWinners.initializeWriter();
				WriteWinners.writeWinners(game.getPlayers(), game.getHallOfFame());
				break;
			case 4:
				ReadWinners.extractPlayers();
				ArrayList<Player> w = ReadWinners.getWinners();
				ArrayList<Player> p = ReadWinners.getPlayers();
				for (Player winner : w) {
					System.out.println("Il "+ winner.getVictories()+" volte vincitore "+winner.getName()+" contro: \n");
					for (Player player : p) {
						System.out.println(player.getName()+" con "+player.getRemainingCards()+" carte rimanenti");
					}
				}
				break;
			case 5:
				ReadDeck.chooseFilename();
				System.out.println(ReadDeck.getReadDeck().toString());
				break;
			case 6:
				System.out.println("1. Mazzo pregenerato dal porgramma");
				System.out.println("2. Mazzo importato");
				int c = InputDati.leggiIntero(">");
				switch (c) {
				case 1:
					break;
				case 2:
					game.setGameDeck(ReadDeck.getReadDeck());
					game.getGameDeck().shuffle();
					break;
				default:
					System.out.println("Comando non trovato");
					break;
				}
				break;
			case 7:
				loop = !InputDati.yesOrNo("Vuoi davvero terminare il programma (tutti i dati non salvati andranno persi)?");
				break;
			default:
				System.out.println("Comando non trovato");
				break;
			}
		}while (loop);
	}
}
