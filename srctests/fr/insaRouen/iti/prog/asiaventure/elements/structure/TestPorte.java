package fr.insaRouen.iti.prog.asiaventure.elements.structure;

import org.junit.Test;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Clef;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.serrurerie.Serrure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestPorte {
    @Test
    public void testPieceAutreCote() throws Exception {
        Monde monde = new Monde("monde");
        Piece pA = new Piece("pA", monde);
        Piece pB = new Piece("pB", monde);
        Porte porte = new Porte("porte", monde, pA, pB);

        assertThat(porte.getPieceAutreCote(pA), equalTo(pB));
        assertThat(porte.getPieceAutreCote(pB), equalTo(pA));
    }

    @Test
    public void testOuvrirPorte() throws Exception {
        Monde monde = new Monde("monde");
        Piece pA = new Piece("pA", monde);
        Piece pB = new Piece("pB", monde);
        Serrure serrure = new Serrure("serrure", monde);
        Clef clef = serrure.getClef();
        Porte porte_verrouillee = new Porte("porte", monde, serrure, pA, pB);

        assertThat(porte_verrouillee.getEtat(), equalTo(Etat.VERROUILLE));
        porte_verrouillee.activer();
        assertThat(porte_verrouillee.getEtat(), equalTo(Etat.VERROUILLE));
        porte_verrouillee.activerAvec(clef);
        assertThat(porte_verrouillee.getEtat(), equalTo(Etat.OUVERT));
        porte_verrouillee.activer();
        assertThat(porte_verrouillee.getEtat(), equalTo(Etat.FERME));
        porte_verrouillee.activer();
        assertThat(porte_verrouillee.getEtat(), equalTo(Etat.OUVERT));
        porte_verrouillee.activerAvec(clef);
        assertThat(porte_verrouillee.getEtat(), equalTo(Etat.VERROUILLE));
        porte_verrouillee.activerAvec(clef);
        assertThat(porte_verrouillee.getEtat(), equalTo(Etat.OUVERT));
    }
}
