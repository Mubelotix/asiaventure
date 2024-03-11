package fr.insaRouen.iti.prog.asiaventure.elements.structure;

import org.junit.Test;
import fr.insaRouen.iti.prog.asiaventure.Monde;
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
}
