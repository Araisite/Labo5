import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MotActuel {
	
	
	String mot;
	
	Dictionnaire dictionnaire;
	String affichage="";
	boolean verif=false;
	String definition="";
	

	
	
	
	public MotActuel (Dictionnaire dictionnaire) {
		
		mot = "";
		this.dictionnaire = dictionnaire;		
	}
	
	
	public void setMot (char lettreTappe) {
		
		String motAffiche = "";
		definition = "";
		
		lettreTappe = Character.toLowerCase(lettreTappe);
		
		LexiNode lexinode = new LexiNode ();
		lexinode = dictionnaireInitial(lexinode);
		
		if (lettreTappe != ' ') {
			
			if (lettreTappe == '\b') {
				
				mot = mot.substring(0, mot.length()-1);
			}
			else {
				mot = mot + lettreTappe;
			}
			
			
			
			affichage = "";
			

			
			lexinode = lexinode.noeudCourant(mot);
		
		}	
	
				
		construireDcitionnaire(lexinode, motAffiche, verif);
		dictionnaireDefinition(lexinode);
		
		//Condition d'affichage d'une lettre qui n'est pas dans les choix du dictionnaire
		if (lexinode.getEnfants().size() == 0 && lexinode.getDefinition().isEmpty()) {
			
			affichage = "";
		}
	                
	}
	
	
	public void construireDcitionnaire(LexiNode lexinode, String motaffiche, boolean verif) {
		
		String motTemp = motaffiche + lexinode.getLettreAcutelle();
		
		
		if (verif == false) {
			
			motTemp = motaffiche;
		}
		
		
		if (lexinode.getEnfants().size() == 0 || lexinode.getDefinition().contentEquals("")==false) {
			
			
			affichage = affichage + mot + motTemp + '\n';
			motaffiche = "";
		}
		
		
		for (int i=0; i<lexinode.getEnfants().size() ; i++) {	
			
			
			
			construireDcitionnaire(lexinode.getEnfants().get(i), motTemp, true);
			
			
		}
	
		
			
	}


	public LexiNode dictionnaireInitial (LexiNode lexinode) {
	
		for(int i=0; i<dictionnaire.getNbrMots(); i++) {
			
			for(int j=0; j<dictionnaire.getNbrMots(); j++) {
			
				if(dictionnaire.getMot(i).contentEquals(dictionnaire.getMot(j))) {
					
					
					if (j!=i) {
						
						String def = dictionnaire.getLigne(i) + '\n' + '\n' + dictionnaire.getDefinition(j);
						
						dictionnaire.setDefinition(def, i);
						dictionnaire.deleteMot(j);
						
					}
				}
			}
	
		
		}
		
		for(int i=0; i<dictionnaire.getNbrMots(); i++) {
				
			
				lexinode.ajouterMot(dictionnaire.getMot(i), dictionnaire.getDefinition(i));
		}
		return lexinode;
	}
	
	public String getAffichage () {
		
		return affichage;
	}
	public String getDef () {
		
		return definition;
	}
	
	public void dictionnaireDefinition(LexiNode lexinode) {
		
		
		if (lexinode.getDefinition().contentEquals("")==false) {
		
			definition = lexinode.getDefinition();
		}
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
}
