package fr.insaRouen.iti.prog.asiaventure.elements.objets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Test;
import fr.insaRouen.iti.prog.asiaventure.ASIAventureException;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationException;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.serrurerie.Serrure;

public class TestSerrure {
    @Test
    public void testSerrure() throws ASIAventureException {
        Monde monde = new Monde("monde");
        Serrure serrure = new Serrure("serrure", monde);
        Clef clef = serrure.creerClef();

        assertThat(serrure.getEtat(), equalTo(Etat.VERROUILLE));
        serrure.activerAvec(clef);
        assertThat(serrure.getEtat(), equalTo(Etat.DEVERROUILLE));
        serrure.activerAvec(clef);
        assertThat(serrure.getEtat(), equalTo(Etat.VERROUILLE));
    }

    @Test(expected = ActivationException.class)
    public void testSerrureMauvaiseClef() throws ASIAventureException {
        Monde monde = new Monde("monde");
        Serrure serrure = new Serrure(monde);
        Serrure serrure2 = new Serrure(monde);
        Clef clef = serrure.creerClef();
        Clef clef2 = serrure2.creerClef();

        assertThat(serrure.getEtat(), equalTo(Etat.VERROUILLE));
        serrure.activerAvec(clef2);
        assertThat(serrure.getEtat(), equalTo(Etat.VERROUILLE));
    }
}
