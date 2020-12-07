package mainLab;

import java.util.ArrayList;
import java.util.LinkedList;

/**

 @author Philippe Meuser, Corentin Seguin
 @version 1.0
 @since 2020/12/06
 */
public class LexiNode {
	
	private char lettreActuelle;
	private String definition;
	private String motCourant;
	private ArrayList<LexiNode> enfants = new ArrayList<LexiNode>();

	
	public LexiNode () {
		
		lettreActuelle = ' ';
		definition = "";
		motCourant ="";
	}
		
	public LexiNode (char lettreActuelle, String motCourant, String definition) {

		this.lettreActuelle = lettreActuelle;
		this.motCourant = motCourant;
		this.definition = definition;		
	}
	
	public void ajouterMot (String mot, String definition) {
		
	
		
		//Condition de fin de la r�cursivit�, quand le mot est vide
		if (mot.isEmpty() != true) {
			
		
			//Premi�re lettre du mot
			char lettreCourante = mot.charAt(0);
			
			//Initialisation d'une d�finition temporaire
			String defTemp="";
			
			
			//Boucle qui parcours les enfants existants du noeud
			for (int i=0; i<enfants.size(); i++) {
				
				//Condition pour savoir si l'enfants existe deja
				if(enfants.get(i).lettreActuelle == lettreCourante) {
					
					//R�cursivit�, on acc�de � l'enfant 
					enfants.get(i).ajouterMot(mot.substring(1), definition);
					return;
				}
			}
			
			//Si le mot n'est pas vide, definition � null
			if (mot.substring(1).isEmpty() == false) {
				
				defTemp = "";
			}
			else {
				
				defTemp = definition;
			}
			
			//cr�ation du noeud enfant
			LexiNode noeud = new LexiNode(lettreCourante, motCourant+lettreCourante, defTemp);
			enfants.add(noeud);
					
			if (mot.substring(1).contentEquals(" ")==false) {
				
				noeud.ajouterMot(mot.substring(1), definition);
			}
		}	
		//Condition : si mot vide, mais qui poss�de des enfants
		else {
			if (enfants.size()>0) {
		
				this.definition = definition;
			}
		}
		
		
	}
	
	
	
	
	
	public LexiNode noeudCourant (String mot) {
		
		LexiNode temp = this;
		boolean verif=false;
		
		
		
		if (mot.length()!=0) {
			
			char lettreCourante  = mot.charAt(0);
			
			for (int i=0; i<enfants.size(); i++) {
				
				if (lettreCourante == enfants.get(i).lettreActuelle) {
					
					verif = true;
				}
			}
			if(verif==false) {
				
				return new LexiNode();
			}
		
			for (int j=0; j<enfants.size(); j++) {

				if (lettreCourante == enfants.get(j).lettreActuelle) {
					
					temp = enfants.get(j).noeudCourant(mot.substring(1));
					
					return temp;
				}
			}		
		}
		return temp;
	}
	
	public ArrayList<LexiNode> getEnfants() {
		return enfants;
	}

	public char getLettreAcutelle() {
		return lettreActuelle;
	}
	
	public String getDefinition() {
		return definition;
	}
	
	public void printChoix(String mot, String motTappe) {
		if (mot.length()!=0) {
			char lettreCourante  = mot.charAt(0);
			for (int j=0; j<enfants.size(); j++) {
				
				if (lettreCourante == enfants.get(j).lettreActuelle) {
				
					enfants.get(j).printChoix(mot.substring(1), motTappe);
					return;
				}
			}
			return;
		}
		printReste (motTappe);
	}
	
	public void printReste(String mot) {
		for (int i=0; i<enfants.size(); i++) {
			enfants.get(i).printReste(mot+enfants.get(i).lettreActuelle);
		}
		if (enfants.isEmpty()==true) {
			System.out.println(mot);
		}
	}
}
