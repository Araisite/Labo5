package mainLab;

import java.io.*;

/**
 Cette classe sert à ajouter un nouveau mot et une définition à un fichier temporaraire txt
 @author Philippe Meuser, Corentin Seguin
 @version 1.0
 @since 2020/12/06
 */
public class AjouterMotFichier{


    //private static File nomDuFichier;
    private static File copieTemporaire = new File("X");
    private static File source = new File("");

    /**
       Ce constructeur a besoin du mot de la définition et de l'adresse du fichier source
       @param mot le mot à ajouter
       @param def la définition à ajouter
       @param adresse l'adresse de destination du fichier
       @throws IOException
     */
    public AjouterMotFichier(String mot, String def, String adresse) throws IOException {
        AjouterMot(mot,def, adresse);
    }

    /**
       Cette méthode permet d'ajouter un mot au fichier selectionné,
       elle va d'abord créer une copie du fichier source avant d'ajouter le mot et la définition à celui-ci
       @param mot le mot à ajouter
       @param def la définition à ajouter
       @param adresse l'adresse de destination du fichier
     */
    public void AjouterMot(String mot, String def, String adresse) {

        File sourceTemp = new File(adresse);
        source = sourceTemp;

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

    /**
       Cette méthode permet d'obtenir l'adresse du fichier temporaire créé
       @return l'adresse du fichier temporaire
     */
    public static File getFichier(){return copieTemporaire;}

    /**
       Cette méthode supprimme le fichier temporaire
     */
    public static void supFichier(){ copieTemporaire.delete();}

    /**
     * Cette méthode permet de choisir le nom du nouveau fichier
     * @param nom
     */
    public static void choisirNomFicher(String nom) {

        String ancienne = source.getPath();
        String temp = source.getName();
        System.out.println(temp);

        while (temp.length()!=0) {

            ancienne = ancienne.substring(0, ancienne.length()-1);
            temp = temp.substring(0, temp.length()-1);
        }

        File dest = new File(ancienne+nom+".txt");

        source.renameTo(dest);
    }
    }
