import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Fichier {

	public static LinkedList<String> obtenirMotsDefinitions() {
		
		//D�claration de la liste � retourner
		LinkedList <String> dictionnaire = new LinkedList <String> ();
		
		//Cr�er un file chooser
		final JFileChooser fc = new JFileChooser();
		
		//Cr�er un nouveau bouton
		JButton open = new JButton();
		
		//En r�ponse au bouton cliqu�
		fc.showOpenDialog(open); 
		
		//Try-Catch pour l'ouverture du fichier
		try {
					
			//D�claration du scanner
			Scanner scan = new Scanner (fc.getSelectedFile());
			
			//it�rateur
			int i=0;
			
			while (scan.hasNextLine()) {
			
				dictionnaire.add(i, scan.nextLine());
			}
			
			//fermeture du scanner
			scan.close();
			
		//Si le fichier n'est pas trouv�, lanc� l'exception StackTrace
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
			System.exit(1);
		}
		
		//retour de la liste avec definitions et mots
		return dictionnaire;
	}
}
