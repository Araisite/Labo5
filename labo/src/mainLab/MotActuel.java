package mainLab;

/**
 Cette classe permet de savoir le mot qui est entrain d'etre ecrit et de connaitre par la suite tout les mots qui ont la meme base
 @author Philippe Meuser, Corentin Seguin
 @version 1.0
 @since 2020/12/06
 */
public class MotActuel {
	
	
	String mot;
	Dictionnaire dictionnaire;
	String affichage="";
	boolean verif=false;
	String definition="";
	
	
	/*--- CONSTRUCTEUR ---*/
	
	//Par copie d'attributs

	public MotActuel (Dictionnaire dictionnaire) {
		
		mot = "";
		this.dictionnaire = dictionnaire;		
	}
	
	/**
	 * Cette méthode permet d'instancier l'arbre du LexiNode et de l'adapter sleon les choix de l'utilisateir
	 * @param char lettreTappe, lettre ecrite par l'utilisateur
	 * @return 
	 */
	public void setMot (char lettreTappe) {
		
		String motAffiche = "";
		definition = "";
		
		//Convertir ce qui est ecrit par l'utilsateur en miniscule, uniformisation 
		lettreTappe = Character.toLowerCase(lettreTappe);
		
		//Création du LexiNode vide
		LexiNode lexinode = new LexiNode ();
		
		//Création du LexiNode initial, avec tous les mots
		lexinode = dictionnaireInitial(lexinode);
		
		//Condition qu'il y ait queqlue chose de tappé
		if (lettreTappe != ' ') {
			
			//Condition si l'utilisateur entre un backspace
			if (lettreTappe == '\b') {
				
				//Réduire le mot pour conrespondre au besoin de l'utilisateur
				mot = mot.substring(0, mot.length()-1);
			}
			else {
				//Ajouter la lettre tappé au mot
				mot = mot + lettreTappe;
			}
			
			//Remise à 0 de l'affichage des mots possibles
			affichage = "";
			
			//Aller chercher le lexiNode du mot, donc de tous les enfants de la dernière lettre tappée
			lexinode = lexinode.noeudCourant(mot);
		
		}
		
		//Construire le dictionnaire des mots restants selon le neodu actuel
		construireDcitionnaire(lexinode, motAffiche, verif);
		
		//Si le mot actuel comporte une definition, aller la chercher
		dictionnaireDefinition(lexinode);
		
		//Condition d'affichage d'une lettre qui n'est pas dans les choix du dictionnaire
		if (lexinode.getEnfants().size() == 0 && lexinode.getDefinition().isEmpty()) {
			
			affichage = "";
		}
	}
	
	
	/**
	 * Cette méthode permet de construire le dictionnaire selon les choix de l'utilisateur, donc les choix restants
	 * @param LexiNode lexinode, le noeud actuel
	 * @param String motaffiche, le mot affihce
	 * @param boolean verif, verification qu'on est au deebut de la récursivité
	 * @return 
	 */	
	public void construireDcitionnaire(LexiNode lexinode, String motaffiche, boolean verif) {
		
		//Mot temporaire definit par le motaffiche et la lettre du lexinode actuel
		String motTemp = motaffiche + lexinode.getLettreAcutelle();
		
		//Si verif est faux, on continu, donc seulement pour le premier passage 
		if (verif == false) {
			
			//Le mot temporaire devient le mot affihce
			motTemp = motaffiche;
		}
		
		//Condition qu'il n'y ai pas d'enfants ou que le mot a une definition
		if (lexinode.getEnfants().size() == 0 || lexinode.getDefinition().contentEquals("")==false) {
			
			//Le mot actuel est donc un mot à afficher
			affichage = affichage + mot + motTemp + '\n';
			
			//Remise à 0 du mot affiche
			motaffiche = "";
		}
	
		//Boucle qui parcourt les enfants actuels
		for (int i=0; i<lexinode.getEnfants().size() ; i++) {
			
			//Reprise pour les enfants, récursivié d'affichage
			construireDcitionnaire(lexinode.getEnfants().get(i), motTemp, true);
		}
	}

	/**
	 * Cette méthode permet de construire le dictionnaire initial, quand rien n'est ecrit, l'arbre complet
	 * @param LexiNode lexinode, le noeud actuel
	 * @return LexiNode complet (arbre complet)
	 */	
	public LexiNode dictionnaireInitial (LexiNode lexinode) {
	
		//Pour tous les mots du dictionnaire
		for(int i=0; i<dictionnaire.getNbrMots(); i++) {
			
			////Parcours aussi tous les mots du dictionnaire
			for(int j=0; j<dictionnaire.getNbrMots(); j++) {
				
				//si le dictionnaire contient 2x le meme mot
				if(dictionnaire.getMot(i).contentEquals(dictionnaire.getMot(j))) {
					
					//Verification que ce n'est pas au même endroit
					if (j!=i) {
						
						//Merge des definitions dans le même mot
						String def = dictionnaire.getLigne(i) + '\n' + '\n' + dictionnaire.getDefinition(j);
						dictionnaire.setDefinition(def, i);
						dictionnaire.deleteMot(j);
					}
				}
			}
		}
		
		//ajoute les mots et definitions à l'arbre lexinode
		for(int i=0; i<dictionnaire.getNbrMots(); i++) {
				lexinode.ajouterMot(dictionnaire.getMot(i), dictionnaire.getDefinition(i));
		}
		return lexinode;
	}
	
	/**
	 * Cette méthode permet d'avoir accès à l'affichage des mots
	 * @param String
	 */
	public String getAffichage () {
		return affichage;
	}

	/**
	 * Cette méthode permet d'avoir à la definition actuel du mot
	 * @param String
	 */
	public String getDef () {
		return definition;
	}

	/**
	 * Cette méthode permet d'avoir toutes les définitions du lexinode
	 * @param lexinode
	 */
	public void dictionnaireDefinition(LexiNode lexinode) {
		if (lexinode.getDefinition().contentEquals("")==false) {
			definition = lexinode.getDefinition();
		}
	}

	/**
	 * Cette méthode permet de connaitre un mot
	 * @return le mot assigné en mot
	 */
	public String getMot() {
		return mot;
	}
}
