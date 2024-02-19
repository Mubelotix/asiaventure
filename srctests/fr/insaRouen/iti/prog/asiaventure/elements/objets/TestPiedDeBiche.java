package srctests.fr.insaRouen.iti.prog.asiaventure.elements.objets;

import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.PiedDeBiche;

public class TestPiedDeBiche {
    Monde monde;
    PiedDeBiche pdb;

    @Before
    public void initialisation() throws NomDEntiteDejaUtiliseDansLeMondeException {
        this.monde = new Monde("monde");
        this.pdb = new PiedDeBiche("pdb", this.monde);
    }

    @Test
    public void testPiedDeBiche() {
        assertThat(this.pdb.estDeplacable(), is(true));
    }
}
