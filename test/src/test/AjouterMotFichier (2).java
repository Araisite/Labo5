

import java.io.*;

public class AjouterMotFichier {


    //private static File nomDuFichier;
    private static File copieTemporaire = new File("Documents");
    private static File source = new File("");

    public AjouterMotFichier(String mot, String def, String adresse) throws IOException {
        AjouterMot(mot,def, adresse);
    }

    public void AjouterMot(String mot, String def, String adresse) {

    	File sourceTemp = new File(adresse);
    	source = sourceTemp;
    	
        BufferedWriter bufEcrire = null;
        FileWriter ecrire = null;

        try (InputStream sourceFile = new java.io.FileInputStream(source);
             OutputStream destinationFile = new FileOutputStream(copieTemporaire)) {
            // Lecture par segment de 0.5Mo
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            //destinationFile.write(Fichier.obtenirMotsDefinitions());
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
            System.out.println("Copie");

        } catch (IOException e) {
            e.printStackTrace();
        }


            try{
            //File nomDuFichier = File.createTempFile("data", null);
            ecrire = new FileWriter(copieTemporaire, true); // Permet d'ecrire dans le fichier sans supprimer son contenu
            bufEcrire = new BufferedWriter(ecrire);
            bufEcrire.newLine(); // Saut a la ligne
            bufEcrire.write(mot + " & " + def);
            System.out.println(copieTemporaire.getCanonicalPath());

            System.out.println("Ajout");
        } catch (IOException e) {
            e.printStackTrace();

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
        public static File getFichier(){return copieTemporaire;}

        public static void supFichier(){ copieTemporaire.delete();}
    }
