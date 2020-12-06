package test;

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

import javax.swing.*;
import javax.swing.border.Border;


public class LayoutPrincipal extends JFrame implements ActionListener {

	private JFrame frameMain = new JFrame();

	private JPanel pnlMain = new JPanel(new BorderLayout());
	private JPanel pnlBouton = new JPanel(new FlowLayout());
	private JPanel pnlDef = new JPanel();
	private JPanel pnlListe = new JPanel();
	private JPanel pnlChercher = new JPanel();

	private JButton btnCharger = new JButton("Charger");
	private JButton btnEnregistrer = new JButton("Enregistrer");
	private JButton btnAjouter = new JButton("ajouter/modifier");


	private JLabel label1 = new JLabel("cherche");
	private JLabel label2 = new JLabel("def");
	private JLabel label3 = new JLabel("terrible");

	private JTextArea afficheDef = new JTextArea("");
	private JTextArea afficheChercher = new JTextArea();

	private JTextField tfMot = new JTextField(16);


	JPanel buttonContainer = new JPanel();

	
	private Dictionnaire testDico = new Dictionnaire();
	private MotActuel motActuel = new MotActuel(testDico);
	
	private int i = 0;
	private JButton[] myButton = new JButton[testDico.getNbrMots()];
	

	public LayoutPrincipal() {

		/*  */
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

		for (i = 0; i < testDico.getNbrMots(); i++) { // Affichage de la liste de mot
			myButton[i] = new JButton(testDico.getMot(i));
			buttonContainer.add(myButton[i]);
			buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
		}
		/* Ajout de scroll barre */
		JScrollPane scrollListe = new JScrollPane(buttonContainer, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnlListe.add(scrollListe);
		pnlMain.add(pnlListe, BorderLayout.EAST);


		/* Panneau du Centre */
		pnlDef.setLayout(new BoxLayout(pnlDef, BoxLayout.PAGE_AXIS));
		pnlDef.setPreferredSize(new Dimension(400, 100));
		pnlDef.setMaximumSize(new Dimension(400, 100));
		pnlDef.setBorder(BorderFactory.createTitledBorder("Definition"));
		pnlDef.add(afficheDef);
		afficheDef.setLineWrap(true);

		pnlMain.add(pnlDef, BorderLayout.CENTER);

		/* Panneau de Gauche */
		tfMot.addKeyListener(new MonEcouteurClavier());

		pnlChercher.setLayout(new BorderLayout());
		pnlChercher.setPreferredSize(new Dimension(200, 100));
		pnlChercher.setMaximumSize(new Dimension(200, 100));
		pnlChercher.setBorder(BorderFactory.createTitledBorder("Chercher"));
		pnlChercher.add(tfMot, BorderLayout.NORTH);
		
		pnlChercher.add(afficheChercher);
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

		if (event.getSource() == btnEnregistrer) {
			try {
				String adresse = testDico.getAdresse();
				new EnregistrerFichierTXT(adresse);
				AjouterMotFichier.supFichier();
				Dictionnaire dictAjout = new Dictionnaire (adresse);
				testDico = dictAjout;
				
				String motInstant = motActuel.getMot();
				MotActuel temp = new MotActuel (dictAjout);
				motActuel = temp;
				System.out.print(motInstant);
				for (int i=0; i<motInstant.length(); i++ ) {
					motActuel.setMot(motInstant.charAt(i));
				}
				
				afficheChercher.setText(motActuel.getAffichage());
				
			} catch (IOException e) {
				e.printStackTrace(); // Trace les erreurs et les affiches dans le terminal
				/* cree un pop up d'alerte*/
				JOptionPane alerteEnregistrer = new JOptionPane();
				JOptionPane.showMessageDialog(alerteEnregistrer,
						"Veuillez ajouter/modifier un mot avant d'enregistrer.",
						"Erreur enregistrement",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		if (event.getSource() == btnAjouter) {
			String mot = tfMot.getText();
			String def = afficheDef.getText();
			String adresse = testDico.getAdresse();
			try {
				new AjouterMotFichier(mot, def, adresse);
			} catch (IOException | NullPointerException e) {
				e.printStackTrace(); // Trace les erreurs et les affiches dans le terminal
				/* cree un pop up d'alerte*/
				JOptionPane alerteAjouter = new JOptionPane();
				JOptionPane.showMessageDialog(alerteAjouter,
						"Erreur d'ajout ou de modification.",
						"Erreur ajouter/modifier",
						JOptionPane.WARNING_MESSAGE);
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
			
			
			
		}
	}

}


