
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

	private JTextField tfMot = new JTextField(16);


	JPanel buttonContainer = new JPanel();

	
	private Dictionnaire testDico = new Dictionnaire();
	private MotActuel motActuel = new MotActuel(testDico);
	
	private int i = 0;
	private JButton[] myButton = new JButton[testDico.getNbrMots()];
	
	private JTextArea afficheChercher = new JTextArea();

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

		for (i = 0; i < testDico.getNbrMots(); i++) { // Affichage de la liste de mot
			myButton[i] = new JButton(testDico.getMot(i));
			buttonContainer.add(myButton[i]);
			buttonContainer.setLayout(new BoxLayout(buttonContainer, BoxLayout.Y_AXIS));
			myButton[i].addActionListener(this::actionPerformed);
		}
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
		if (event.getSource() == myButton) {
			afficheDef.setText(testDico.getDefinition(2));
			pnlChercher.repaint();
		}
		if (event.getSource() == btnEnregistrer) {

		}
		if (event.getSource() == btnAjouter) {

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


