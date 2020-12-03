
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AjouterMotFichier {


    private File nomDuFichier = new File("C:\\Users\\cocos\\Documents\\test.txt");

    public AjouterMotFichier(String mot, String def) {
        AjouterMot(mot,def);
    }

    public void AjouterMot(String mot, String def) {

        BufferedWriter bufEcrire = null;
        FileWriter ecrire = null;

        try {
            ecrire = new FileWriter(nomDuFichier, true); // Permet d'ecrire dans le fichier sans supprimer son contenu
            bufEcrire = new BufferedWriter(ecrire);
            bufEcrire.newLine(); // Saut a la ligne
            bufEcrire.write(mot + " & " + def);

            System.out.println("Done");

        } catch (IOException e) {

            //e.printStackTrace();

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
    }
