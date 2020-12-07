package mainLab;

import java.io.*;

/**
 Cette classe sert � ajouter un nouveau mot et une d�finition � un fichier temporaraire txt
 @author Philippe Meuser, Corentin Seguin
 @version 1.0
 @since 2020/12/06
 */
public class AjouterMotFichier{

    //private static File nomDuFichier;
    private static File copieTemporaire = new File("X");
    private static File source = new File("");

    /**
       Ce constructeur a besoin du mot de la d�finition et de l'adresse du fichier source
       @param mot le mot � ajouter
       @param def la d�finition � ajouter
       @param adresse l'adresse de destination du fichier
       @throws IOException
     */
    public AjouterMotFichier(String mot, String def, String adresse) throws IOException {
    	
    	//Fichier temporaire et actuel
    	File sourceTemp = new File(adresse);
        source = sourceTemp;
        
        //Buffer et wrtier utile
        BufferedWriter bufEcrire = null;
        FileWriter ecrire = null;

        try (InputStream sourceFile = new java.io.FileInputStream(source);
             OutputStream destinationFile = new FileOutputStream(copieTemporaire)) {

        	//Lecture par segment de 0.5Mo
            byte buffer[] = new byte[512 * 1024]; 
            
            //Nombre de lectures
            int nbLecture;
            
            // Lit le fichier a copier et ecrit sur le fichier de destination 
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
  
        //Regarde pour les exceptions
        } catch (IOException e) {
        	
        	// Trace les erreurs et les affiches dans le terminal
            e.printStackTrace(); 
        }


        try{
        	// Permet d'ecrire dans le fichier sans supprimer son contenu
            ecrire = new FileWriter(copieTemporaire, true); 
            bufEcrire = new BufferedWriter(ecrire);
            bufEcrire.newLine(); // Saut a la ligne
            bufEcrire.write(mot + " & " + def);
           
            
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
       Cette m�thode permet d'obtenir l'adresse du fichier temporaire cr��
       @return l'adresse du fichier temporaire
     */
    public static File getFichier(){
    	
    	return copieTemporaire;
    }

    /**
       Cette m�thode supprimme le fichier temporaire
     */
    public static void supFichier(){ 
    	
    	copieTemporaire.delete();
    }

    /**
     * Cette m�thode permet de choisir le nom du nouveau fichier
     * @param nom
     */
    public static void choisirNomFicher(String nom) {

    	//Prend le path du fichier actuellement utiliser
        String ancienne = source.getPath();
        
        //Prend le nom du fihcier actuellement utiliser
        String temp = source.getName();
     
        //Boucle qui parcourt le nom du fichier actuellement utiliser
        while (temp.length()!=0) {

        	//R�duire le path actuelle de un jusqu'� ce que le nom du fichier soit effac�
            ancienne = ancienne.substring(0, ancienne.length()-1);
            temp = temp.substring(0, temp.length()-1);
        }

        //Renommer le fichier avec le nouveau nom choisi
        File dest = new File(ancienne+nom+".txt");
        source.renameTo(dest);
    }
}
