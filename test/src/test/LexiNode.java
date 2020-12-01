package test;

import java.util.ArrayList;
import java.util.LinkedList;

public class LexiNode {
	
	private char lettreAcutelle;
	private String definition;
	private String motCourant;
	private ArrayList<LexiNode> enfants;

	
	
	public LexiNode (char lettreAcutelle, String motCourant, String definition) {

		this.lettreAcutelle = lettreAcutelle;
		this.motCourant = motCourant;
		this.definition = definition;
		
		
	}
	
	public void ajouterMot (String mot, String definition) {
		
		
		char lettreCourante = mot.charAt(0);
		String defTemp;
		
		for (int i=0; i<enfants.size(); i++) {
			
			if(enfants.get(i).lettreAcutelle == lettreCourante) {
				
				enfants.get(i).ajouterMot(mot.substring(1), definition);
				return;
			}
		}
			
		if (mot.substring(1)!="") {
			
			defTemp = "";
		}
		else {
			
			defTemp = definition;
		}
		
		LexiNode noeud = new LexiNode(lettreCourante, motCourant+lettreCourante, defTemp);
		enfants.add(noeud);
				
		if (mot.substring(1) !="") {
			
			noeud.ajouterMot(mot.substring(1), definition);
		}	
	}
	
	public void modifierMot (String mot) {
		
		
	}
	
	public void printChoix(String mot) {
		
		char lettreCourante = mot.charAt(0);
		
		for (int i=0; i<enfants.size(); i++) {
			
			if(enfants.get(i).lettreAcutelle == lettreCourante) {
				
				System.out.print(lettreCourante);
				enfants.get(i).printChoix(mot.substring(1));
				
				return;
			}
		}
		
		
	}

		 
}

