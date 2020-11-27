import java.util.ArrayList;
import java.util.LinkedList;

public class Dictionnaire {

	private LinkedList <String> dictionnaire = new LinkedList <String> ();
	
	public Dictionnaire (){
		
		Fichier fichier = new Fichier();
		
		dictionnaire = fichier.obtenirMotsDefinitions();
	}
	
	public String getMot(int i) {
		
		String [] separation;
		
		separation = dictionnaire.get(i).split("&");
		separation[0].trim();
		
		return separation[0];
	}
	
	public String getDefinition(int i) {
		
		String [] separation;
		
		separation = dictionnaire.get(i).split("&");
		separation[1].trim();
		
		return separation[1];
	}
	
	public int getNbrMots () {
		
		return dictionnaire.size();
	}
	

	
	
	public void updateDictionnaire (char noeud, int index) {
		
		
		
		for (int i=0; i<dictionnaire.size(); i++) {
			
			
			if (noeud != getMot(i).charAt(index)) {
				
								
				dictionnaire.remove(i);
				i=i-1;
				
				System.out.println("XXX");
				
			}
			
		}

	}
	
}
