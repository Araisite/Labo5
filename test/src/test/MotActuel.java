package test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MotActuel {
	
	
	String mot = "";
	test.Dictionnaire dictionnaire = new Dictionnaire();
	
	public void setMot (char lettreTappe) {
		
		mot = mot + lettreTappe;
		
		System.out.print(mot);
		
		
		test.LexiNode test2 = new LexiNode(lettreTappe, dictionnaire, mot.length()-1);
		dictionnaire = test2.getDictionnaire();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
