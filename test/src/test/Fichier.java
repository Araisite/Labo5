import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Fichier {

	public static LinkedList<String> obtenirMotsDefinitions() {
		
		//Déclaration de la liste à retourner
		LinkedList <String> dictionnaire = new LinkedList <String> ();
		
		//Créer un file chooser
		final JFileChooser fc = new JFileChooser();
		
		//Créer un nouveau bouton
		JButton open = new JButton();
		
		//En réponse au bouton cliqué
		fc.showOpenDialog(open); 
		
		//Try-Catch pour l'ouverture du fichier
		try {
					
			//Déclaration du scanner
			Scanner scan = new Scanner (fc.getSelectedFile());
			
			//itérateur
			int i=0;
			
			while (scan.hasNextLine()) {
			
				dictionnaire.add(i, scan.nextLine());
			}
			
			//fermeture du scanner
			scan.close();
			
		//Si le fichier n'est pas trouvé, lancé l'exception StackTrace
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
			System.exit(1);
		}
		
		//retour de la liste avec definitions et mots
		return dictionnaire;
	}
}
