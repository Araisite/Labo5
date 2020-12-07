package mainLab;

import java.util.LinkedList;

/**
 Cette classe sert � r�cupr�rer les informations textes sur le fichier s�lectionn�
 @author Philippe Meuser, Corentin Seguin
 @version 1.0
 @since 2020/12/06
 */
public class Dictionnaire {

	private LinkedList <String> dictionnaire = new LinkedList <String> ();
	private String adresse;
	
	
	/*--- CONSTRUCTEUR ---*/
	
	//Par default
	public Dictionnaire (){
		
		//Creation d'un nouveau fichier
		Fichier fichier = new Fichier();
		
		//Dictionnaire devient la liste de mots et definitions
		dictionnaire = fichier.obtenirMotsDefinitions();
		
		//Adresse du fichier cherche
		adresse = fichier.getAdresse();
		
		//Uniformisation des caracteres du dictionnaire
		for (int i=0; i<dictionnaire.size();i++) {
			
			String min = dictionnaire.get(i).toLowerCase();
			dictionnaire.set(i, min);
		}
		
	}
	
	//Par copie d'attributs
	public Dictionnaire (String adresse) {
		
		//Creation d'un nouveau fichier
		Fichier fichier = new Fichier();
		
		//Dictionnaire devient la liste de mots et definitions
		dictionnaire = fichier.directionFicher(adresse);
		
		//Adresse du fichier cherche
		adresse = fichier.getAdresse();
		
		//Uniformisation des caracteres du dictionnaire
		for (int i=0; i<dictionnaire.size();i++) {
			
			String min = dictionnaire.get(i).toLowerCase();
			dictionnaire.set(i, min);
		}
	}
	
	
	/**
	 * Cette m�thode permet de r�cup�rer un mot dans le dictionnaire source
	 * @param i position du mot
	 * @return le mot situ� avant la separation
	 */
	public String getMot(int i) {
		
		String [] separation;
		
		//Separation du mot et de sa definition
		separation = dictionnaire.get(i).split("&");
	
		//Trim le mot avant de le retourner
		return separation[0].trim();
	}

	/**
	 * Cette m�thode permet de r�cup�rer une d�finition dans le dictionnaire source
	 * @param i position de la d�finition
	 * @return la d�finition situ�e apres la s�paration
	 */
	public String getDefinition(int i) {
		
		String [] separation;
		
		//Separation du mot et de sa definition
		separation = dictionnaire.get(i).split("&");
		
		//Trim la definition avant de la retourner
		return separation[1].trim();
	}

	/**
	 * Cette m�thode permet de connaitre le nombre de mot pr�sent dans notre dictionnaire
	 * @return le nombre de mot
	 */
	public int getNbrMots () {
		
		return dictionnaire.size();
	}

	/**
	 * Cette m�thode permet de savoir si notre dictionnaire contient un mot sp�cifique
	 * @param mot le mot que l'on cherche dans le dictionnaire
	 * @return si le dictionnaire contient ce mot ou non
	 */
	public boolean contenirDictionnaire(String mot) {
		
		boolean verif = false;
		
		//Verifie si le mot est dans la liste
		if (dictionnaire.contains(mot)) {
			verif = true;
		}
		return verif;
	}

	/**
	 * Cette m�thode permet de supprimer un mot � une position
	 * @param i position du mot a supprimer
	 */
	public void deleteMot (int i) {
		
		dictionnaire.remove(i);
	}

	/**
	 * Cette m�thode permet d'impl�menter une d�finition � une position
	 * @param def d�finition � impl�menter
	 * @param i position pour ecrire la d�finition
	 */
	public void setDefinition (String def, int i) {
		
		dictionnaire.set(i, def);
	}

	/**
	 * Cette m�thode permet de connaitre le contenu d'une ligne � l'aide de la position
	 * @param i position dans le dictionnaire
	 * @return le contenu de la ligne choisie
	 */
	public String getLigne(int i) {
		
		return dictionnaire.get(i);
	}

	/**
	 * Cette m�thode permet de connaitre l'adresse du fichier source
	 * @return l'adresse du fichier source
	 */
	public String getAdresse() {
		
		return adresse;
	}
}
