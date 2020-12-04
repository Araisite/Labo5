
import java.io.*;



public class EnregistrerFichierTXT {

    public EnregistrerFichierTXT() throws IOException { EnregitrerFichier();}

    public void EnregitrerFichier() throws IOException {
        // Le fichier de sauvegarde
        File saveDictio = new File("C:\\Users\\cocos\\Documents\\dictionnaire.txt");

        try (InputStream sourceFile = new java.io.FileInputStream(AjouterMotFichier.getFichier());
             OutputStream destinationFile = new FileOutputStream(saveDictio)) {
            // Lecture par segment de 0.5Mo
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            //destinationFile.write(Fichier.obtenirMotsDefinitions());
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
            System.out.println("Sauvegarde");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
