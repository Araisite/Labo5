import java.util.ArrayList;

/**
Cette classe sert à représenter un noeuyd de l'arbre avec ses enfants et sa definition
@author Philippe Meuser, Corentin Seguin
@version 1.0
@since 2020/12/06
*/
public class LexiNode {
	
	private char lettreAcutelle;
	private String definition;
	private String motCourant;
	private ArrayList<LexiNode> enfants = new ArrayList<LexiNode>();

	/*--- CONSTRUCTEUR ---*/
	
	//par default
	public LexiNode () {
		
		lettreAcutelle = ' ';
		definition = "";
		motCourant ="";
	}
		
	//par copie d'ttributs
	public LexiNode (char lettreAcutelle, String motCourant, String definition) {

		this.lettreAcutelle = lettreAcutelle;
		this.motCourant = motCourant;
		this.definition = definition;		
	}
	
	
	/**
	 * Cette méthode permet d'ajouter un mot à l'arbre du dictionnaire, récusrif
	 * @param String mot, le mot demandé à être ajouter, modifier avec la récusivité
	 * @param String definition, qui accompagne le mot demandé
	 */
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
			
			//Ajout de l'enfant à la liste
			enfants.add(noeud);
					
			//Condition que le mot n'est pas vide
			if (mot.substring(1).contentEquals(" ")==false) {
				
				//Récursivité avec l'enfant
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
	
	
	/**
	 * Cette méthode permet de trouver le noeud courant dans l'arbre total
	 * @param String mot, le mot demandé pour trouver son noeud
	 * @return LexiNode le noeud recherché
	 */	
	public LexiNode noeudCourant (String mot) {
		
		LexiNode temp = this;
		boolean verif=false;
		
		//Condition du mot pas vide
		if (mot.length()!=0) {
			
			//Première lettre du mot
			char lettreCourante  = mot.charAt(0);
			
			//Boucle qui parcours les enfants existants du noeud
			for (int i=0; i<enfants.size(); i++) {
				
				//Si lettre courante est la même que celle du noeud
				if (lettreCourante == enfants.get(i).lettreAcutelle) {
					
					verif = true;
				}
			}
			
			//Si la boucle n'a rien trouvé, retour d'un lexiNode vide
			if(verif==false) {
				
				return new LexiNode();
			}
			
			//Parcours à nouveau les enfants
			for (int j=0; j<enfants.size(); j++) {
						
				//Si lettre courante est la même que celle du noeud
				if (lettreCourante == enfants.get(j).lettreAcutelle) {
					
					//Réduction du mot
					temp = enfants.get(j).noeudCourant(mot.substring(1));
					
					return temp;
				}
			
			}		
		}
	
		return temp;
	}
	
	
	/**
	 * Cette méthode permet d'avoir accès à la liste d'enfants du noeud
	 * @return Liste de LExiNode
	 */	
	public ArrayList<LexiNode> getEnfants() {
		return enfants;
	}


	/**
	 * Cette méthode permet d'avoir accès à la lettre actuelle du noeud
	 * @return char lettreActuelle du noeud
	 */	
	public char getLettreAcutelle() {
		return lettreAcutelle;
	}
	
	/**
	 * Cette méthode permet d'avoir accès à la definition reliée au noeud
	 * @return String defintiion actuelle du noeud
	 */	
	public String getDefinition() {
		return definition;
	}
}
