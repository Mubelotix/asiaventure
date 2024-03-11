package fr.insaRouen.iti.prog.asiaventure;

import org.junit.Before;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

class EntiteTest extends Entite {
    public EntiteTest(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }
}

public class TestMonde {
    Monde monde;

    @Before
    public void init() {
        this.monde = new Monde("monde");
    }

    @Test
    public void testNomMonde() {
        assertThat(this.monde.getNom(), is("monde"));
    }

    @Test
    public void testGetEntite() throws Exception {
        assertThat(this.monde.getEntite("entite"), IsEqual.equalTo(null));
        EntiteTest entite = new EntiteTest("entite", this.monde);
        assertThat(this.monde.getEntite("entite"), IsEqual.equalTo(entite));
    }

    @Test(expected = NomDEntiteDejaUtiliseDansLeMondeException.class)
    public void testAjouterEntiteNomDejaUtilise() throws Exception {
        new EntiteTest("entite", this.monde);
        new EntiteTest("entite", this.monde);
    }

    @Test(expected = EntiteDejaDansUnAutreMondeException.class)
    public void testAjouterEntiteDejaDansUnAutreMonde() throws Exception {
        Monde monde2 = new Monde("monde2");
        EntiteTest entite = new EntiteTest("entite", this.monde);
        monde2.ajouter(entite);
    }
}
