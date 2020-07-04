package it.unibs.ing.arnaldo.unoghioh;

import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class ReadWinners {
	static String filename = "Winners.xml";
	static XMLStreamReader xmlr = null;
	static ArrayList<Player> players = new ArrayList<>();
	static ArrayList<Player> winners = new ArrayList<>();
	
	public static void initializeReader() {
		try {
			xmlr = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(filename));
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void extractPlayers() {
		try {
			while(xmlr.hasNext()) {
				switch(xmlr.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:
					break;
				case XMLStreamConstants.START_ELEMENT:
					switch(xmlr.getLocalName()) {
					case "partita":
						break;
					case "vincitore":
						Player w = new Player(xmlr.getAttributeValue(0));
						w.setVictories(Integer.parseInt(xmlr.getAttributeValue(1)));
						winners.add(w);				
						break;
					case "giocatore":
						Player p = new Player(xmlr.getAttributeValue(0));
						p.setRemainingCards(Integer.parseInt(xmlr.getAttributeValue(1)));
						players.add(p);
						break;
					}
					break;
				 case XMLStreamConstants.END_ELEMENT:
					 break;
				 case XMLStreamConstants.COMMENT:
					 break; 
				 case XMLStreamConstants.CHARACTERS:
					 break;
				}
				xmlr.next();
			}
		} catch (Exception e) {}
	}

	public static ArrayList<Player> getPlayers() {
		return players;
	}

	public static ArrayList<Player> getWinners() {
		return winners;
	}

	public static void setPlayers(ArrayList<Player> players) {
		ReadWinners.players = players;
	}

	public static void setWinners(ArrayList<Player> winners) {
		ReadWinners.winners = winners;
	}
	
	
	
}
