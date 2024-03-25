package fr.insaRouen.iti.prog.asiaventure.elements.objets.serrurerie;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.is;

import org.junit.Test;
import fr.insaRouen.iti.prog.asiaventure.ASIAventureException;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.serrurerie.Serrure;

public class TestSerrure {
    
    @Test
    public void testSerrure() throws ASIAventureException {
        Monde monde = new Monde("monde");
        Serrure serrure = new Serrure("serrure", monde);
        Clef clef = serrure.getClef();

        assertThat(serrure.getEtat(), equalTo(Etat.VERROUILLE));
        serrure.activerAvec(clef);
        assertThat(serrure.getEtat(), equalTo(Etat.DEVERROUILLE));
        serrure.activerAvec(clef);
        assertThat(serrure.getEtat(), equalTo(Etat.VERROUILLE));
    }

    @Test
    public void testSerrureMauvaiseClef() throws ASIAventureException {
        Monde monde = new Monde("monde");
        System.out.println("debut");
        Serrure serrure = new Serrure(monde);
        Serrure serrure2 = new Serrure(monde);
        Clef clefdifferente = new Clef("cle_0", monde);
        Clef clef2 = serrure2.getClef();
        System.out.println("fin");
        

        assertThat(serrure.getEtat(), equalTo(Etat.VERROUILLE));
        assertThat(serrure.activableAvec(clef2), is(false));
        
    }
}
