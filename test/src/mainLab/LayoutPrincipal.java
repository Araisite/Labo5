package mainLab;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;


public class LayoutPrincipal extends JFrame implements ActionListener {

	private Dictionnaire testDico = new Dictionnaire();
	private MotActuel motActuel = new MotActuel(testDico);

	private JFrame frameMain = new JFrame();

	private JPanel pnlMain = new JPanel(new BorderLayout());
	private JPanel pnlBouton = new JPanel(new FlowLayout());
	private JPanel pnlDef = new JPanel();
	private JPanel pnlListe = new JPanel();
	private JPanel pnlChercher = new JPanel();

	private JButton btnCharger = new JButton("Charger");
	private JButton btnEnregistrer = new JButton("Enregistrer");
	private JButton btnAjouter = new JButton("ajouter/modifier");
	private JButton[] myButton = new JButton[testDico.getNbrMots()];

	private JTextArea afficheDef = new JTextArea("");
	private JTextArea afficheChercher = new JTextArea();
	private JTextArea listeDeMots = new JTextArea();

	private JTextField tfMot = new JTextField(16);

	private JFrame nameFrame = new JFrame();
	private JTextField nomFichier = new JTextField(16);

	private String nomDuFichier ="";
	private boolean test = false;


	public LayoutPrincipal() {


		this.btnCharger.addActionListener(this);
		pnlBouton.add(this.btnCharger);

		this.btnEnregistrer.addActionListener(this);
		pnlBouton.add(this.btnEnregistrer);

		pnlMain.add(pnlBouton, BorderLayout.NORTH);

		this.btnAjouter.addActionListener(this);
		pnlMain.add(btnAjouter, BorderLayout.SOUTH);

		/* Panneau de Droite */
		pnlListe.setLayout(new BoxLayout(pnlListe, BoxLayout.Y_AXIS));
		pnlListe.setPreferredSize(new Dimension(300, 100));
		pnlListe.setMaximumSize(new Dimension(300, 100));
		pnlListe.setBorder(BorderFactory.createTitledBorder("Liste Mots"));

		String listeDroite ="";

		for (int i = 0; i < testDico.getNbrMots(); i++) { // Affichage de la liste de mot

			listeDroite = listeDroite + testDico.getMot(i) + '\n' ;
		}
		listeDeMots.setText(listeDroite);
		pnlListe.add(listeDeMots);


		JScrollPane scrollListe = new JScrollPane(listeDeMots, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnlListe.add(scrollListe);
		pnlMain.add(pnlListe, BorderLayout.EAST);



		/* Panneau du Centre */
		pnlDef.setLayout(new BoxLayout(pnlDef, BoxLayout.PAGE_AXIS));
		pnlDef.setPreferredSize(new Dimension(400, 100));
		pnlDef.setMaximumSize(new Dimension(400, 100));
		pnlDef.setBorder(BorderFactory.createTitledBorder("Definition"));
		pnlDef.add(afficheDef);
		afficheDef.setLineWrap(true);
		//pnlDef.add(Dictionnaire.getDefinition(1));

		pnlMain.add(pnlDef, BorderLayout.CENTER);

		/* Panneau de Gauche */
		tfMot.addKeyListener(new MonEcouteurClavier());

		pnlChercher.setLayout(new BorderLayout());
		pnlChercher.setPreferredSize(new Dimension(200, 100));
		pnlChercher.setMaximumSize(new Dimension(200, 100));
		pnlChercher.setBorder(BorderFactory.createTitledBorder("Chercher"));
		pnlChercher.add(tfMot, BorderLayout.NORTH);

		pnlChercher.add(afficheChercher);
		JScrollPane scrollMots = new JScrollPane(afficheChercher, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnlChercher.add(scrollMots);


		pnlMain.add(pnlChercher, BorderLayout.WEST);




		/* Panneau Principal */
		frameMain.add(pnlMain);
		frameMain.setTitle("DICTIO");
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameMain.pack();
		frameMain.setSize(900, 500);
		frameMain.setLocationRelativeTo(null);
		frameMain.setVisible(true);
		frameMain.setResizable(false);


		motActuel.setMot(' ');
		afficheChercher.setText(motActuel.getAffichage());

	}



	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == myButton[2]) {
			afficheDef.setText(motActuel.getDef());
			pnlChercher.repaint();
		}
		if (event.getSource() == btnEnregistrer) {
			try {
				nameFrame.setTitle("Choisir nom du fichier ; appuyer sur Entrer pour fermer");
				nameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				nameFrame.pack();
				nameFrame.setSize(500, 60);
				nameFrame.setLocationRelativeTo(null);
				nameFrame.setVisible(true);
				nameFrame.setResizable(false);
				nameFrame.add(nomFichier);

				nomFichier.addKeyListener(new MonEcouteurClavier2());

				new EnregistrerFichierTXT(testDico.getAdresse());
				AjouterMotFichier.supFichier();

				Dictionnaire dictAjout = new Dictionnaire (testDico.getAdresse());
				testDico = dictAjout;

				String motInstant = motActuel.getMot();
				MotActuel temp = new MotActuel (dictAjout);
				motActuel = temp;
				System.out.print(motInstant);
				for (int i=0; i<motInstant.length(); i++ ) {
					motActuel.setMot(motInstant.charAt(i));
				}

				afficheChercher.setText(motActuel.getAffichage());

				String listeDroite ="";

				for (int i = 0; i < testDico.getNbrMots(); i++) { // Affichage de la liste de mot
					listeDroite = listeDroite + testDico.getMot(i) + '\n' ;
				}
				listeDeMots.setText(listeDroite);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (event.getSource() == btnAjouter) {
			String mot = tfMot.getText();
			String def = afficheDef.getText();
			try {
				new AjouterMotFichier(mot,def, testDico.getAdresse());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (event.getSource() == btnCharger) {

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


	public class MonEcouteurClavier implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

			motActuel.setMot(e.getKeyChar());
			afficheChercher.setText("");
			afficheDef.setText("");
			afficheChercher.setText(motActuel.getAffichage());
			afficheDef.setText(motActuel.getDef());

			System.out.print(e.getKeyChar());

		}
	}

	public class MonEcouteurClavier2 implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyPressed(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

			if (e.getKeyChar() == '\b') {

				nomDuFichier = nomDuFichier.substring(0, nomDuFichier.length()-1);
			}

			else if (e.getKeyCode() == KeyEvent.VK_ENTER)	 {

				AjouterMotFichier.choisirNomFicher(nomDuFichier);
				nameFrame.dispose();

			}

			else {
				nomDuFichier = nomDuFichier + e.getKeyChar();
			}






		}
	}

}


