package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AjouterMotFichier {


    //private static File nomDuFichier;
    private static File nomDuFichier = new File("C:\\Users\\cocos\\Documents\\test.txt");


    public AjouterMotFichier(String mot, String def) throws IOException {
        AjouterMot(mot,def);
    }

    public void AjouterMot(String mot, String def) {

        BufferedWriter bufEcrire = null;
        FileWriter ecrire = null;

        try {
            //File nomDuFichier = File.createTempFile("data", null);
            ecrire = new FileWriter(nomDuFichier, true); // Permet d'ecrire dans le fichier sans supprimer son contenu
            bufEcrire = new BufferedWriter(ecrire);
            bufEcrire.newLine(); // Saut a la ligne
            bufEcrire.write(mot + " & " + def);
            System.out.println(nomDuFichier.getCanonicalPath());

            System.out.println("Done");
        } catch (IOException e) {


        } finally {

            try {

                if (bufEcrire != null)
                    bufEcrire.close();

                if (ecrire != null)
                    ecrire.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }
        public static File getFichier(){return nomDuFichier;}

        public static void supFichier(){ nomDuFichier.delete();}
    }
