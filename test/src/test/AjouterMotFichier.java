package test;

import java.io.*;

public class AjouterMotFichier extends Dictionnaire{


    //private static File nomDuFichier;
    private static File copieTemporaire = new File("Documents");
    private static File source = new File("");

    public AjouterMotFichier(String mot, String def, String adresse) throws IOException {
        AjouterMot(mot,def, adresse);
    }

    public void AjouterMot(String mot, String def, String adresse) {

            File source = new File(adresse);


        BufferedWriter bufEcrire = null;
        FileWriter ecrire = null;

        try (InputStream sourceFile = new java.io.FileInputStream(source);
             OutputStream destinationFile = new FileOutputStream(copieTemporaire)) {

            byte buffer[] = new byte[512 * 1024]; // Lecture par segment de 0.5Mo
            int nbLecture;
            /* Lit le fichier a copier et ecrit sur le fichier de destination */
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
            System.out.println("Copie");

        } catch (IOException e) {
            e.printStackTrace(); // Trace les erreurs et les affiches dans le terminal
        }


            try{
            ecrire = new FileWriter(copieTemporaire, true); // Permet d'ecrire dans le fichier sans supprimer son contenu
            bufEcrire = new BufferedWriter(ecrire);
            bufEcrire.newLine(); // Saut a la ligne
            bufEcrire.write(mot + " & " + def);
            System.out.println(copieTemporaire.getCanonicalPath()); // Affiche le chemin d'acces du txt temporaire

            System.out.println("Ajout");
        } catch (IOException e) {
            e.printStackTrace(); // Trace les erreurs et les affiches dans le terminal

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
