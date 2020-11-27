import java.util.ArrayList;
import java.util.LinkedList;

public class LexiNode {
	
	private char noeud;
	private char nextNoeud;
	private String definition;
	private LinkedList <LexiNode> enfants = new LinkedList <LexiNode> ();
	private Dictionnaire dictionnaire;

	
	
	public LexiNode (char noeud, Dictionnaire dictionnaire, int index) {
		
		System.out.println(noeud);
		this.noeud = noeud;
		this.dictionnaire = dictionnaire;
	
		
		//Dictionnaire avec seulement les mots utiles
		dictionnaire.updateDictionnaire(noeud, index);
		
		index=index+1;
		
		//Parcours la liste de mots restants
		for (int i=0; i<dictionnaire.getNbrMots(); i++) {
			
			//Si mot complet, ajoute la definition
			if (index == dictionnaire.getMot(i).length()) {
				
				definition = dictionnaire.getDefinition(i);
			}
			
			//Si la lettre suivante pas deja un enfant, l'ajouter	
			if (enfants.contains(dictionnaire.getMot(i).charAt(index)) == false) {
				
				LexiNode enfantTemp = new LexiNode (dictionnaire.getMot(i).charAt(index), dictionnaire, index);
				enfants.add(enfantTemp);
				
			}			
		}		
	}


	public char getNoeud() {
		return noeud;
	}


	public void setNoeud(char noeud) {
		this.noeud = noeud;
	}


	public String getDefinition() {
		return definition;
	}


	public void setDefinition(String definition) {
		this.definition = definition;
	}


	public LinkedList<LexiNode> getEnfants() {
		return enfants;
	}


	public void setEnfants(LinkedList<LexiNode> enfants) {
		this.enfants = enfants;
	}
	
	public Dictionnaire getDictionnaire() {
		return dictionnaire;
	}
	
}
