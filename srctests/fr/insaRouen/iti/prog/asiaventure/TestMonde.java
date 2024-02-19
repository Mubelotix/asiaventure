package srctests.fr.insaRouen.iti.prog.asiaventure;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import fr.insaRouen.iti.prog.asiaventure.Monde;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
}
