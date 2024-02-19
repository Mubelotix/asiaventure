package fr.insaRouen.iti.prog.asiaventure.elements.objets;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;

class ObjetTest extends Objet {
    public ObjetTest(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    public boolean estDeplacable(){
        return false;
    }
}

public class TestObjet {
    Monde monde;
    ObjetTest objet;

    @Before
    public void initialisation() throws NomDEntiteDejaUtiliseDansLeMondeException {
        this.monde = new Monde("monde");
        this.objet = new ObjetTest("objet", this.monde);
    }

    @Test
    public void testObjet() {
        assertThat(this.objet.estDeplacable(), is(false));
    }
}
