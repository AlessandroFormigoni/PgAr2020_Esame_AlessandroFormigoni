package it.unibs.ing.arnaldo.unoghioh;
public class Main {

	public static void main(String[] args) {
		ReadWinners.initializeReader();
		ReadWinners.extractPlayers();
		WriteWinners.initializeWriter();
		WriteWinners.writeWinners(ReadWinners.getPlayers(), ReadWinners.getWinners());
		Util.gameMenu(Util.initialize());
	}
}
