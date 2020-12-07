

import javax.swing.*;
import java.io.*;

/**
 Cette classe sert � enregistrer le fichier temporaraire cr�� par la classe AjouterMotFichier
 @author Philippe Meuser, Corentin Seguin
 @version 1.0
 @since 2020/12/06
 */
public class EnregistrerFichierTXT {

	
	/** 
	 *  Ce ocnstructeur de copier le fichier temporaire et de cr�er un nouveau .txt
	 * @param adresse adresse du fichier de destination, qui est le fichier source
	 * @throws IOException si l'addresse est introuvable
	 */
    public EnregistrerFichierTXT(String adresse) throws IOException { 
    
    	// Le fichier de sauvegarde
        File saveDictio = new File(adresse);

        try (InputStream sourceFile = new java.io.FileInputStream(AjouterMotFichier.getFichier());
             OutputStream destinationFile = new FileOutputStream(saveDictio)) {
        	
            // Lecture par segment de 0.5Mo
            byte buffer[] = new byte[512 * 1024];
            
            //Nombre de lectures
            int nbLecture;
            
            /* lit le fichier a copier et ecrit sur le fichier de destination avec un comptage du nombre de fois n�cessaire pour copier*/
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Trace les erreurs et les affiches dans le terminal
            
            /* cree un pop up d'alerte*/
            JOptionPane alerteEnregistrer = new JOptionPane();
            JOptionPane.showMessageDialog(alerteEnregistrer,
                    "Veuillez ajouter/modifier un mot avant d'enregistrer.",
                    "Erreur enregistrement",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
