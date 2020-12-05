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
                while ((nbLecture = sourceFile.read(buffer)) != -1) {
                    destinationFile.write(buffer, 0, nbLecture);
                }
                System.out.println("Sauvegarde");

            } catch (FileNotFoundException | NullPointerException e) {
                //e.printStackTrace();
                System.err.println("Erreur enregistrement, Veuillez ajouter ou modifier pour pouvoir enregistrer");
                JFrame frameErreur = new JFrame();
                JOptionPane.showMessageDialog(frameErreur,
                        "Veuillez ajouter ou modifier pour pouvoir enregistrer",
                        "Erreur enregistrement!!",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

