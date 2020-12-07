package mainLab;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 Cette classe sert à récuprérer les informations textes sur le fichier sélectionné
 @author Philippe Meuser, Corentin Seguin
 @version 1.0
 @since 2020/12/06
 */
public class Dictionnaire {

	private LinkedList <String> dictionnaire = new LinkedList <String> ();
	private String adresse;
	
	public Dictionnaire (){
		
		Fichier fichier = new Fichier();
		
		dictionnaire = fichier.obtenirMotsDefinitions();
		
		adresse = fichier.getAdresse();
		
		for (int i=0; i<dictionnaire.size();i++) {
			
			String min = dictionnaire.get(i).toLowerCase();
			dictionnaire.set(i, min);
		}
		
	}
	
	public Dictionnaire (String adresse) {
		
		Fichier fichier = new Fichier();
		
		dictionnaire = fichier.directionFicher(adresse);
		
		adresse = fichier.getAdresse();
		
		for (int i=0; i<dictionnaire.size();i++) {
			
			String min = dictionnaire.get(i).toLowerCase();
			dictionnaire.set(i, min);
		}
	}

	/**
	 * Cette méthode permet de récupérer un mot dans le dictionnaire source
	 * @param i position du mot
	 * @return le mot situé avant la separation
	 */
	public String getMot(int i) {
		
		String [] separation;
		
		separation = dictionnaire.get(i).split("&");
	
		
		return separation[0].trim();
	}

	/**
	 * Cette méthode permet de récupérer une définition dans le dictionnaire source
	 * @param i position de la définition
	 * @return la définition située apres la séparation
	 */
	public String getDefinition(int i) {
		
		String [] separation;
		
		separation = dictionnaire.get(i).split("&");
		
		
		return separation[1].trim();
	}

	/**
	 * Cette méthode permet de connaitre le nombre de mot présent dans notre dictionnaire
	 * @return le nombre de mot
	 */
	public int getNbrMots () {
		
		return dictionnaire.size();
	}

	/**
	 * Cette méthode permet de savoir si notre dictionnaire contient un mot spécifique
	 * @param mot le mot que l'on cherche dans le dictionnaire
	 * @return si le dictionnaire contient ce mot ou non
	 */
	public boolean contenirDictionnaire(String mot) {
		
		boolean verif = false;
		
		if (dictionnaire.contains(mot)) {
			verif = true;
		}
		return verif;
	}

	/**
	 * Cette méthode permet de supprimer un mot à une position
	 * @param i position du mot a supprimer
	 */
	public void deleteMot (int i) {
		
		dictionnaire.remove(i);
	}

	/**
	 * Cette méthode permet d'implémenter une définition à une position
	 * @param def définition à implémenter
	 * @param i position pour ecrire la définition
	 */
	public void setDefinition (String def, int i) {
		
		dictionnaire.set(i, def);
	}

	/**
	 * Cette méthode permet de connaitre le contenu d'une ligne à l'aide de la position
	 * @param i position dans le dictionnaire
	 * @return le contenu de la ligne choisie
	 */
	public String getLigne(int i) {
		
		return dictionnaire.get(i);
	}

	/**
	 * Cette méthode permet de connaitre l'adresse du fichier source
	 * @return l'adresse du fichier source
	 */
	public String getAdresse() {
		
		return adresse;
	}
}
