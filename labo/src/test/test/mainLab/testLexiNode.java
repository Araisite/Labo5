package mainLab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**

 @author Philippe Meuser, Corentin Seguin
 @version 1.0
 @since 2020/12/06
 */
public class testLexiNode{

    LexiNode test1 = new LexiNode();

    @Test
    void testGetLettreACtuelle(){
        test1.ajouterMot("gaufre","subst. fém. Gâteau de miel alvéolé, confectionné par les abeilles.");
        Assertions.assertEquals('e',test1.noeudCourant("gaufre").getLettreAcutelle());
    }

    @Test
    void testGetDefinition(){
        test1.ajouterMot("gaufre","subst. fém. Gâteau de miel alvéolé, confectionné par les abeilles.");
        Assertions.assertEquals("subst. fém. Gâteau de miel alvéolé, confectionné par les abeilles." ,test1.noeudCourant("gaufre").getDefinition());
    }

    @Test
    void testGetEnfant(){
        test1.ajouterMot("gaufre","subst. fém. Gâteau de miel alvéolé, confectionné par les abeilles.");
        Assertions.assertEquals( new ArrayList<LexiNode>(),test1.noeudCourant("gaufre").getEnfants());
    }

    @Test
    void testNoeudCourant(){ // on a du comparer les 3 éléments qui le compose
        test1.ajouterMot("gaufre","subst. fém. Gâteau de miel alvéolé, confectionné par les abeilles.");
        Assertions.assertEquals('e',test1.noeudCourant("gaufre").getLettreAcutelle());
        Assertions.assertEquals("subst. fém. Gâteau de miel alvéolé, confectionné par les abeilles." ,test1.noeudCourant("gaufre").getDefinition());
        Assertions.assertEquals( new ArrayList<LexiNode>(),test1.noeudCourant("gaufre").getEnfants());
    }

    @Test
    void testAjouterMot(){
        Assertions.assertEquals(test1.noeudCourant("").getLettreAcutelle(), test1.getLettreAcutelle());
        test1.ajouterMot("gaufre","subst. fém. Gâteau de miel alvéolé, confectionné par les abeilles.");
        Assertions.assertEquals( "subst. fém. Gâteau de miel alvéolé, confectionné par les abeilles.", test1.noeudCourant("gaufre").getDefinition());
    }
}
