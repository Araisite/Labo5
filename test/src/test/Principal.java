package test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Principal {

	/*public static void main(String[] args) {

		//Dictionnaire test = new Dictionnaire();
		//LexiNode test2 = new LexiNode('B', test, 0);

		//Démarrage du thread
		MotActuel mot = new MotActuel();
		Thread t = new Thread ();
		t.start();
	}*/


    public static void main( String[] args ) {

        new LayoutPrincipal().setVisible(true);
        /* Démarrage du thread */
    }
}