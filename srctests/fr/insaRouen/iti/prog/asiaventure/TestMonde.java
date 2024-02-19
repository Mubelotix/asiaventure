package fr.insaRouen.iti.prog.asiaventure;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import fr.insaRouen.iti.prog.asiaventure.Monde;
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
        monde = new Monde("monde");
    }

    @Test
    public void testNomMonde() {
        assertThat(monde.getNom(), is("monde"));
    }

    @Test
    public void testGetEntite() throws Exception {
        assertThat(monde.getEntite("entite"), IsEqual.equalTo(null));
        EntiteTest entite = new EntiteTest("entite", monde);
        assertThat(monde.getEntite("entite"), IsEqual.equalTo(entite));
    }

    @Test(expected = NomDEntiteDejaUtiliseDansLeMondeException.class)
    public void testAjouterEntiteNomDejaUtilise() throws Exception {
        new EntiteTest("entite", monde);
        new EntiteTest("entite", monde);
    }

    @Test(expected = EntiteDejaDansUnAutreMondeException.class)
    public void testAjouterEntiteDejaDansUnAutreMonde() throws Exception {
        Monde monde2 = new Monde("monde2");
        EntiteTest entite = new EntiteTest("entite", monde);
        monde2.ajouter(entite);
    }
}
