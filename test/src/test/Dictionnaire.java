package test;

import java.util.LinkedList;

public class Dictionnaire {

	private LinkedList <String> dictionnaire = new LinkedList <String> ();
	
	public Dictionnaire (){
		
		Fichier fichier = new Fichier();
		
		dictionnaire = fichier.obtenirMotsDefinitions();
		
		for (int i=0; i<dictionnaire.size();i++) {
			
			String min = dictionnaire.get(i).toLowerCase();
			dictionnaire.set(i, min);
		}

	}

	public String getMot(int i) {
		
		String [] separation;
		
		separation = dictionnaire.get(i).split("&");
	
		
		return separation[0].trim();
	}
	
	public String getDefinition(int i) {
		
		String [] separation;
		
		separation = dictionnaire.get(i).split("&");
		
		
		return separation[1].trim();
	}
	
	public int getNbrMots () {
		
		return dictionnaire.size();
	}
	
	public boolean contenirDtcionnaire(String mot) {
		
		boolean verif = false;
		
		if (dictionnaire.contains(mot)) {
			verif = true;
		}
		return verif;
	}	
	
	public void deleteMot (int i) {
		
		dictionnaire.remove(i);
	}
	
	public void setDefinition (String def, int i) {
		
		dictionnaire.set(i, def);
	}
	
	public String getLigne(int i) {
		
		return dictionnaire.get(i);
	}
}
