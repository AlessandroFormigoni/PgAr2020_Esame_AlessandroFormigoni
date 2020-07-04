package it.unibs.ing.arnaldo.unoghioh;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import it.unibs.fp.mylib.InputDati;

public class ReadDeck {
	static String filename = "nucleoBaseUnoGiOh.xml";
	static String baseDirectory = "/home/alessandro/eclipse-workspace/Uno-Gi-Oh!/decks";
	static XMLStreamReader xmlr = null;
	private static Deck deck = new Deck(0);
	
	public static void chooseFilename() {
		File fileList = new File(baseDirectory);
		ArrayList<String> validFiles = new ArrayList<>();
		for(String fileName : fileList.list()) {
				System.out.println("- "+fileName);
				validFiles.add(fileName);
		}
		
		do {
		filename = InputDati.leggiStringaNonVuota("Inserisci il nome del file dal leggere");
		} while (!validFiles.contains(filename));
		filename = baseDirectory+"/"+filename;
		
	}
	
	public static void initializeReader() {
		try {
			xmlr = XMLInputFactory.newInstance().createXMLStreamReader(new FileInputStream(filename));
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void extractCards() {
		try {
			Seed color = null;
			while(xmlr.hasNext()) {
				switch(xmlr.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:
					break;
				case XMLStreamConstants.START_ELEMENT:
					switch(xmlr.getLocalName()) {
					case "mazzo":
						deck.setSize(Integer.parseInt(xmlr.getAttributeValue(0)));
						break;
					case "sottomazzo":
						color = Seed.valueOf(xmlr.getAttributeValue(1).toUpperCase());
						break;
					case "carta":
						try {
						int value = Integer.parseInt(xmlr.getAttributeValue(2));
						deck.addCard(new Card(value,color));
						} catch (NumberFormatException e) {
							String value = xmlr.getAttributeValue(2);
							deck.addCard(new Card(value,color));
						}
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
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static Deck getReadDeck() {
		initializeReader();
		extractCards();
		return deck;
	}
	
	
}
