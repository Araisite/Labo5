
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
Cette classe sert à la cération des GUI
@author Philippe Meuser, Corentin Seguin
@version 1.0
@since 2020/12/06
*/
public class LayoutPrincipal extends JFrame implements ActionListener {

	//Creation du dictionnaire qui va chercher le fichier
	private Dictionnaire testDico = new Dictionnaire();
	
	//Création de l'arbre avec les mots du dictionnaire
	private MotActuel motActuel = new MotActuel(testDico);
	

	/* --- FRAME PRINCIPAL --- */
	
	//Frame
	private JFrame frameMain = new JFrame();

	//Les panels
	private JPanel pnlMain = new JPanel(new BorderLayout());
	private JPanel pnlBouton = new JPanel(new FlowLayout());
	private JPanel pnlDef = new JPanel();
	private JPanel pnlListe = new JPanel();
	private JPanel pnlChercher = new JPanel();

	//Les boutons
	private JButton btnCharger = new JButton("Charger");
	private JButton btnEnregistrer = new JButton("Enregistrer");
	private JButton btnAjouter = new JButton("ajouter/modifier");
	
	//Les text areas
	private JTextArea afficheDef = new JTextArea("");
	private JTextArea afficheChercher = new JTextArea();
	private JTextArea listeDeMots = new JTextArea();

	//Le text field
	private JTextField tfMot = new JTextField(16);
	
	
	/* --- FRAME CHOIX DU NOM DE FICHIER --- */
	
	private JFrame nameFrame = new JFrame();
	private JTextField nomFichier = new JTextField(16);
	private String nomDuFichier ="";
	
	
	/* --- CONSTRUCTEUR DU PANNEAU PRINCIPAL --- */

	//Par default
	public LayoutPrincipal() {

		//Bouton charher
		this.btnCharger.addActionListener(this);
		pnlBouton.add(this.btnCharger);

		//Bouton enregistrer
		this.btnEnregistrer.addActionListener(this);
		pnlBouton.add(this.btnEnregistrer);

		//Panneau de boutons du haut
		pnlMain.add(pnlBouton, BorderLayout.NORTH);

		//Bouton ajouter (bas)
		this.btnAjouter.addActionListener(this);
		pnlMain.add(btnAjouter, BorderLayout.SOUTH);

		/* --- Panneau de Droite --- */
		
		pnlListe.setLayout(new BoxLayout(pnlListe, BoxLayout.Y_AXIS));
		pnlListe.setPreferredSize(new Dimension(300, 100));
		pnlListe.setMaximumSize(new Dimension(300, 100));
		pnlListe.setBorder(BorderFactory.createTitledBorder("Liste Mots"));
		
		//Liste de mots à afficher à droite
		String listeDroite ="";
		
		// Affichage de la liste de mot, parcours la liste
		for (int i = 0; i < testDico.getNbrMots(); i++) { 
			
			listeDroite = listeDroite + testDico.getMot(i) + '\n' ;
		}
		listeDeMots.setText(listeDroite);	
		pnlListe.add(listeDeMots);
		
		//Scrollers
		JScrollPane scrollListe = new JScrollPane(listeDeMots, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnlListe.add(scrollListe);
		
		pnlMain.add(pnlListe, BorderLayout.EAST);
		
		/* --- Panneau du Centre --- */
		
		pnlDef.setLayout(new BoxLayout(pnlDef, BoxLayout.PAGE_AXIS));
		pnlDef.setPreferredSize(new Dimension(400, 100));
		pnlDef.setMaximumSize(new Dimension(400, 100));
		pnlDef.setBorder(BorderFactory.createTitledBorder("Definition"));
		pnlDef.add(afficheDef);
		afficheDef.setLineWrap(true);
		pnlMain.add(pnlDef, BorderLayout.CENTER);

		/* --- Panneau de Gauche --- */
		
		tfMot.addKeyListener(new MonEcouteurClavier());
		pnlChercher.setLayout(new BorderLayout());
		pnlChercher.setPreferredSize(new Dimension(200, 100));
		pnlChercher.setMaximumSize(new Dimension(200, 100));
		pnlChercher.setBorder(BorderFactory.createTitledBorder("Chercher"));
		pnlChercher.add(tfMot, BorderLayout.NORTH);
		pnlChercher.add(afficheChercher);
		
		//Scrollers
		JScrollPane scrollMots = new JScrollPane(afficheChercher, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnlChercher.add(scrollMots);
		
		pnlMain.add(pnlChercher, BorderLayout.WEST);

		/* --- Panneau Principal --- */
		
		frameMain.add(pnlMain);
		frameMain.setTitle("DICTIO");
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.pack();
		frameMain.setSize(900, 500);
		frameMain.setLocationRelativeTo(null);
		frameMain.setVisible(true);
		frameMain.setResizable(false);

		
		//Début de l'opération avec le dictionnaire vide
		motActuel.setMot(' ');
		afficheChercher.setText(motActuel.getAffichage());
	}

	
	/**
   	Cette méthode est à l'affut de tous les evenements sur les boutons
    @param ActionEvent event
	*/
	@Override
	public void actionPerformed(ActionEvent event) {
		
		//Si le bouton enregistrer est cliqué
		if (event.getSource() == btnEnregistrer) {
			try {
				//Deuxième panneau ouvre
				nameFrame.setTitle("Choisir nom du fichier ; appuyer sur Entrer pour fermer");
				nameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				nameFrame.pack();
				nameFrame.setSize(500, 60);
				nameFrame.setLocationRelativeTo(null);
				nameFrame.setVisible(true);
				nameFrame.setResizable(false);
				nameFrame.add(nomFichier);
								
				//Ajouter ecouteur au panneau
				nomFichier.addKeyListener(new MonEcouteurClavier2());
							
				//Creation d'un EnregistrerFichierText et remplacement de l'ancien
				new EnregistrerFichierTXT(testDico.getAdresse());
				AjouterMotFichier.supFichier();
				
				//Rempalcement du dictionnair epar le nouveau				
				Dictionnaire dictAjout = new Dictionnaire (testDico.getAdresse());
				testDico = dictAjout;
				
				//mot de l'instant
				String motInstant = motActuel.getMot();
				
				//Nouveau mot actuel avec le nouveau dictionnaire
				MotActuel temp = new MotActuel (dictAjout);
				motActuel = temp;
				
				//Mise à jour du dicitionnaire sleon le mot actuellement ecrit
				for (int i=0; i<motInstant.length(); i++ ) {
					motActuel.setMot(motInstant.charAt(i));
				}
				
				//Afficher la bonne liste de mot
				afficheChercher.setText(motActuel.getAffichage());
				
				//remise à 0 de la liste de droite avec les nouveaux mots
				String listeDroite ="";
				
				for (int i = 0; i < testDico.getNbrMots(); i++) { // Affichage de la liste de mot
					
					listeDroite = listeDroite + testDico.getMot(i) + '\n' ;
				}
				listeDeMots.setText(listeDroite);	
				
			//Regard sur les exceptions
			} catch (IOException e) {
				e.printStackTrace();
		
			}
		}
		
		//En cas de clic sur le bouton ajouter
		if (event.getSource() == btnAjouter) {
			
			String mot = tfMot.getText();
			String def = afficheDef.getText();
			
			try {
				
				//Ajouter le fichier grâce à ajoutermotfichier
				new AjouterMotFichier(mot,def, testDico.getAdresse());
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
		//Si le bouton chargé est cliqué
		if (event.getSource() == btnCharger) {
			
			//Reprise des étapes initiales avec un nouveau choix de fichier texte
			Dictionnaire dictCharger = new Dictionnaire ();
			MotActuel newMotActuel = new MotActuel (dictCharger);
			motActuel = newMotActuel;
			motActuel.setMot(' ');
			afficheChercher.setText(motActuel.getAffichage());
			tfMot.setText("");
			afficheDef.setText("");
		}
		
		this.frameMain.revalidate();
	}

	
	/**
	 Cette classe sert à l'écouter clavier du mot tappé
	 @author Philippe Meuser, Corentin Seguin
	 @version 1.0
	 @since 2020/12/06
	 */
	public class MonEcouteurClavier implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {
			
			//Aajuster mot actuel et son lexinode en cas de modification du mot vouli par l'utilisateur
			motActuel.setMot(e.getKeyChar()); 
			afficheChercher.setText("");
			afficheDef.setText("");
			afficheChercher.setText(motActuel.getAffichage());
			afficheDef.setText(motActuel.getDef());			
		}
	}
	
	
	/**
	 Cette classe sert à l'écouter clavier lorsque l'utlisateur choisi le nom de son fichier
	 @author Philippe Meuser, Corentin Seguin
	 @version 1.0
	 @since 2020/12/06
	 */
	public class MonEcouteurClavier2 implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {
			
			//Condition du backsapce, retarait d'un carcatere
			if (e.getKeyChar() == '\b') {
					
					nomDuFichier = nomDuFichier.substring(0, nomDuFichier.length()-1);
			}
			
			//Conditionn de fin du pannel
			else if (e.getKeyCode() == KeyEvent.VK_ENTER)	 {
				
				//Change le nom du fichier et ferme le dossier du changement de nom
				AjouterMotFichier.choisirNomFicher(nomDuFichier);
				nameFrame.dispose();
				
			}
			
			else {
				//Le nouveua nom du fichier s'allonge du caractere demandé par l'utlisateur
				nomDuFichier = nomDuFichier + e.getKeyChar();
			}			
		}
	}
}


