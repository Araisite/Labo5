import java.util.ArrayList;
import java.util.LinkedList;

public class LexiNode {
	
	private char lettreAcutelle;
	private String definition;
	private String motCourant;
	private ArrayList<LexiNode> enfants = new ArrayList<LexiNode>();

	
	public LexiNode () {
		
		lettreAcutelle = ' ';
		definition = "";
		motCourant ="";
	}
		
	public LexiNode (char lettreAcutelle, String motCourant, String definition) {

		this.lettreAcutelle = lettreAcutelle;
		this.motCourant = motCourant;
		this.definition = definition;		
	}
	
	public void ajouterMot (String mot, String definition) {
		
	
		
		//Condition de fin de la récursivité, quand le mot est vide
		if (mot.isEmpty() != true) {
			
		
			//Première lettre du mot
			char lettreCourante = mot.charAt(0);
			
			//Initialisation d'une définition temporaire
			String defTemp="";
			
			
			//Boucle qui parcours les enfants existants du noeud
			for (int i=0; i<enfants.size(); i++) {
				
				//Condition pour savoir si l'enfants existe deja
				if(enfants.get(i).lettreAcutelle == lettreCourante) {
					
					//Récursivité, on accède à l'enfant 
					enfants.get(i).ajouterMot(mot.substring(1), definition);
					return;
				}
			}
			
			//Si le mot n'est pas vide, definition à null
			if (mot.substring(1).isEmpty() == false) {
				
				defTemp = "";
			}
			else {
				
				defTemp = definition;
			}
			
			//création du noeud enfant
			LexiNode noeud = new LexiNode(lettreCourante, motCourant+lettreCourante, defTemp);
			enfants.add(noeud);
					
			if (mot.substring(1).contentEquals(" ")==false) {
				
				noeud.ajouterMot(mot.substring(1), definition);
			}
		}	
		//Condition : si mot vide, mais qui possède des enfants
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
				
				if (lettreCourante == enfants.get(i).lettreAcutelle) {
					
					verif = true;
				}
			}
			if(verif==false) {
				
				return new LexiNode();
			}
			
		
			for (int j=0; j<enfants.size(); j++) {
				
			
				
				if (lettreCourante == enfants.get(j).lettreAcutelle) {
					
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
		return lettreAcutelle;
	}
	
	public String getDefinition() {
		return definition;
	}

	public void modifierMot (String mot) {
		
		
	}
	
	public void printChoix(String mot, String motTappe) {
		
		if (mot.length()!=0) {
			char lettreCourante  = mot.charAt(0);
			
				
			for (int j=0; j<enfants.size(); j++) {
				
				if (lettreCourante == enfants.get(j).lettreAcutelle) {
				
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
		
			
			enfants.get(i).printReste(mot+enfants.get(i).lettreAcutelle);
			
		
		}
		if (enfants.isEmpty()==true) {
			
			System.out.println(mot);
		}
		
		
	}
	


		 
	
}
