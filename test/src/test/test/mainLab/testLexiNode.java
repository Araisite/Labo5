package mainLab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**

 @author Philippe Meuser, Corentin Seguin
 @version 1.0
 @since 2020/12/06
 */
public class testLexiNode{

    LexiNode test1 = new LexiNode();
    test1.ajouterMot("gaufre","subst. fém. Gâteau de miel alvéolé, confectionné par les abeilles.");
    test1.

    @Test
    void testAjouterMot(){ Assertions.assertEquals('e',test1.noeudCourant("gaufre").getLettreAcutelle());
    }

    @Test
    void testTest(){ Assertions.assertEquals( test1.ajouterMot("gaufre","subst. fém. Gâteau de miel alvéolé, confectionné par les abeilles."));}
}
