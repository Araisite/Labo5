
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


public class Principal extends JFrame implements ActionListener {
	
    private JFrame frameMain = new JFrame();
    
    private JPanel pnlMain = new JPanel(new BorderLayout());
    private JPanel pnlBouton = new JPanel(new FlowLayout());
    private JPanel pnlDef = new JPanel();
    private JPanel pnlListe = new JPanel();
    private JPanel pnlChercher = new JPanel();
    
    private JButton btnCharger = new JButton( "Charger" );
    private JButton btnEnregistrer = new JButton( "Enregistrer" );
    private JButton btnAjouter = new JButton("ajouter/modifier");    
    
    private JLabel label1 = new JLabel("cherche");
    private JLabel label2 = new JLabel("def");
    private JLabel label3 = new JLabel("tous");
    
    private JTextField tfMot = new JTextField(16);
        
    JPanel buttonContainer = new JPanel(); 
    JButton myButton = null;
    
    private MotActuel mot = new MotActuel();
    
    public LayoutPrincipal() {
    	
    	
               
        this.btnCharger.addActionListener( this );
        pnlBouton.add( this.btnCharger );
        
        this.btnEnregistrer.addActionListener( this );
        pnlBouton.add( this.btnEnregistrer );
        
        pnlMain.add(pnlBouton, BorderLayout.NORTH);

        this.btnAjouter.addActionListener( this );
        pnlMain.add(btnAjouter, BorderLayout.SOUTH);

        pnlListe.setLayout(new BoxLayout(pnlListe, BoxLayout.PAGE_AXIS));
        pnlListe.setPreferredSize(new Dimension(300, 100));  
        pnlListe.setMaximumSize(new Dimension(300, 100));
        pnlListe.add(label3);
        pnlListe.setBorder(BorderFactory.createTitledBorder("Liste Mots"));
        
        pnlMain.add(pnlListe, BorderLayout.EAST);
        
        pnlDef.setLayout(new BoxLayout(pnlDef, BoxLayout.PAGE_AXIS));
        pnlDef.setPreferredSize(new Dimension(400,100));
        pnlDef.setMaximumSize(new Dimension(400,100));
        pnlDef.setBorder(BorderFactory.createTitledBorder("Definition"));
        pnlDef.add(label2);
        
        pnlMain.add(pnlDef, BorderLayout.CENTER);
        
        
        pnlChercher.setPreferredSize(new Dimension(200,100));
        pnlChercher.setMaximumSize(new Dimension(200,100));
        pnlChercher.setBorder(BorderFactory.createTitledBorder("Chercher"));        
        pnlChercher.add(tfMot);

        pnlMain.add(pnlChercher, BorderLayout.WEST);
         

        for (int i = 0; i < 20; i++) { 
         buttonContainer.setLayout(new GridLayout(15, 1, 0, 0)); 
         myButton = new JButton("This is my button nr. " + i); 
         buttonContainer.add(myButton); 
        } 

        pnlListe.setLayout(new BorderLayout(0, 0)); 

        JScrollPane scrollListe = new JScrollPane(buttonContainer, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS); 
        pnlListe.add(scrollListe);

        
        
        frameMain.add(pnlMain);
        frameMain.setTitle("DICTIO");
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frameMain.pack(); 
        frameMain.setSize(900,500); 
        frameMain.setLocationRelativeTo(null); 
        frameMain.setVisible(true); 
        frameMain.setResizable(false); 
        
        pnlChercher.addKeyListener(new MonEcouteurClavier());
        
    }
    
    @Override public void actionPerformed( ActionEvent event ) {
        if (event.getSource() == btnCharger) {
        	
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
			
			//Selon ce que l'écouteur de la touche reçoit
			switch (e.getKeyCode()) {
				
				case KeyEvent.VK_A : mot.setMot('a');
				System.out.print("TEST");
						   break;
				case KeyEvent.VK_B : mot.setMot('b');
				   		   break;					
				case KeyEvent.VK_C : mot.setMot('c');
		   		   		   break;						
				case KeyEvent.VK_D : mot.setMot('d');
						   break;
				case KeyEvent.VK_E : mot.setMot('e');
						   break;
				case KeyEvent.VK_F : mot.setMot('f');
						   break;
				case KeyEvent.VK_G : mot.setMot('g');
						   break;
				case KeyEvent.VK_H : mot.setMot('h');
					 	   break;
				case KeyEvent.VK_I : mot.setMot('i');
						   break;
				case KeyEvent.VK_J : mot.setMot('j');
						   break;
				case KeyEvent.VK_K : mot.setMot('k');
						   break;
				case KeyEvent.VK_L : mot.setMot('l');
						   break;
				case KeyEvent.VK_M : mot.setMot('m');
						   break;
				case KeyEvent.VK_N : mot.setMot('n');
				 	   	   break;
				case KeyEvent.VK_O : mot.setMot('o');
						   break;
				case KeyEvent.VK_P : mot.setMot('p');
						   break;
				case KeyEvent.VK_Q : mot.setMot('q');
						   break;
				case KeyEvent.VK_R : mot.setMot('r');
						   break; 
				case KeyEvent.VK_S : mot.setMot('s');
						   break;
				case KeyEvent.VK_T : mot.setMot('t');
				 	   	   break;
				case KeyEvent.VK_U : mot.setMot('u');
						   break;
				case KeyEvent.VK_V : mot.setMot('v');
						   break;
				case KeyEvent.VK_W : mot.setMot('w');
						   break;
				case KeyEvent.VK_X : mot.setMot('x');
						   break;
				case KeyEvent.VK_Y : mot.setMot('y');
						   break;
				case KeyEvent.VK_Z : mot.setMot('z');
					   	   break;
			}
		}		
	}
   
}


