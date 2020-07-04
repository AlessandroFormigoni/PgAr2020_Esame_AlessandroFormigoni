package it.unibs.ing.arnaldo.unoghioh;

import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class WriteWinners {

	static String filename = "Winners.xml";
	static String version = "1.0";
	static String encoding = "UTF-8";
	static XMLStreamWriter xmlw = null;
	static XMLOutputFactory xmlof = null;
	
	
	public static void initializeWriter() {
		try {
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filename), encoding);
			xmlw.writeStartDocument();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void writeWinners(ArrayList<Player> players,ArrayList<Player> winners) {
		try {
			for(Player winner : winners) {
			xmlw.writeStartElement("Partita");
			xmlw.writeStartElement("vincitore");
			xmlw.writeAttribute("nome", winner.getName());
			xmlw.writeAttribute("numero_vittorie", Integer.toString(winner.getVictories()));
			xmlw.writeEndElement();
			for (Player player : players) {
				xmlw.writeStartElement("giocatore");
				xmlw.writeAttribute("nome", player.getName());
				xmlw.writeAttribute("carte_rimanenti", Integer.toString(player.cardsInHand()));
				xmlw.writeEndElement();
				}
			}
			xmlw.writeEndElement();
			xmlw.flush();
		} catch(Exception e) {e.printStackTrace();}
	}
}
