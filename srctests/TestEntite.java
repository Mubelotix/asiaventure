package srctests;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

class EntiteTest extends Entite{
    public EntiteTest(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }
}

public class TestEntite {
    Monde monde;
    EntiteTest entite;

    @Before
    public void init() throws NomDEntiteDejaUtiliseDansLeMondeException {
        this.monde = new Monde("monde");
        this.entite = new EntiteTest("entite", this.monde);
    }

    @Test
    public void testNom() {
        assertThat(entite.getNom(), is("entite"));
    }

    @Test(expected = NomDEntiteDejaUtiliseDansLeMondeException.class)
    public void testNomDejaUtilise() throws NomDEntiteDejaUtiliseDansLeMondeException {
        new EntiteTest("entite", this.monde);
    }
}
