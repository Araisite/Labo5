package test;

import javax.swing.*;
import java.io.*;



public class EnregistrerFichierTXT {

    public EnregistrerFichierTXT(String adresse) throws IOException { EnregitrerFichier(adresse);}

    public void EnregitrerFichier(String adresse) throws IOException {
        // Le fichier de sauvegarde
        File saveDictio = new File(adresse);

        try (InputStream sourceFile = new java.io.FileInputStream(AjouterMotFichier.getFichier());
             OutputStream destinationFile = new FileOutputStream(saveDictio)) {
            // Lecture par segment de 0.5Mo
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            /* lit le fichier a copier et ecrit sur le fichier de destination*/
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
            System.out.println("Sauvegarde"); // affiche sauvegarde dans le terminal si aucun probleme pour enregistrement

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
