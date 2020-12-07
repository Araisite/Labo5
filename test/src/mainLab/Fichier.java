import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;

/**

 @author Philippe Meuser, Corentin Seguin
 @version 1.0
 @since 2020/12/06
 */
public class Fichier {
	
	private static String adresse="";
		
	/**
	 * Cette méthode met dans une liste chaînée la liste de mots accompagnées de leurs definitions provenant d'un choix de fichier
	 * @return La liste de mots et definitions
	 */
	public static LinkedList<String> obtenirMotsDefinitions() {
		
		//Déclaration de la liste à retourner
		LinkedList <String> dictionnaire = new LinkedList <String> ();
		
		//Creer un file chooser
		final JFileChooser fc = new JFileChooser();
		
		//Creer un nouveau bouton
		JButton open = new JButton();
		
		//En réponse au bouton clique
		fc.showOpenDialog(open); 
		
		//Try-Catch pour l'ouverture du fichier
		try {
					
			//Déclaration du scanner
			Scanner scan = new Scanner (fc.getSelectedFile());
			
			try {
				adresse = fc.getSelectedFile().getCanonicalPath();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//iterateur
			int i=0;
			
			//boucle de lecture du fichier texte
			while (scan.hasNextLine()) {
			
				dictionnaire.add(i, scan.nextLine());
			}
			
			//fermeture du scanner
			scan.close();
			
		//Si le fichier n'est pas trouve, lance l'exception StackTrace
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
			System.exit(1);
		}
		
		//retour de la liste avec definitions et mots
		return dictionnaire;
	}
	
	public String getAdresse() {
		
		return adresse;
	}


	/**
	 * Cette méthode met dans une liste chaînée la liste de mots accompagnées de leurs definitions provenant d'une adresse sppecifique
	 * @param String adresse, adresse du fichier initial
	 * @return La liste de mots et definitions
	 */
	public static LinkedList<String> directionFicher(String adresse) {
		
		//Declaration de la liste à retourner
		LinkedList <String> dictionnaire = new LinkedList <String> ();

		//Creation du fichier provenant de l'adresse specifique
		File fc = new File (adresse);

		//Try-Catch pour l'ouverture du fichier
		try {
					
			//Déclaration du scanner
			Scanner scan = new Scanner (fc);
			
			try {
				adresse = fc.getCanonicalPath();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//itérateur
			int i=0;
			
			//boucle de lecture du fichier texte
			while (scan.hasNextLine()) {
			
				dictionnaire.add(i, scan.nextLine());
			}
			
			//fermeture du scanner
			scan.close();
			
		//Si le fichier n'est pas trouvé, lancer l'exception StackTrace
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
			System.exit(1);
		}
		
		//retour de la liste avec definitions et mots
		return dictionnaire;
	}
}

