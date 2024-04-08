package fr.insaRouen.iti.prog.asiaventure;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.Reader;
import java.io.StringReader;

import org.hamcrest.core.StringContains;
import org.junit.Test;

public class TestSimulateur {
    @Test
    public void testParsing() throws ASIAventureException {
        String value = """
            Monde "LeMondeImpitoyabledITI"
            Piece "BureauDesNicolas"
            Piece "BureauDuDirecteur"
            Piece "BureauDeClement"
            Piece "BureauDeLaurent"
            Piece "Couloir"
            PorteSerrure "Porte1" "Couloir" "BureauDesNicolas"
            PorteSerrure "Porte2" "Couloir" "BureauDuDirecteur"
            Porte "Porte3" "Couloir" "BureauDeClement"
            Porte "Porte4" "Couloir" "BureauDeLaurent"
            Porte "Trappe" "BureauDeClement" "BureauDuDirecteur"
            Porte "PassageSecret" "BureauDesNicolas" "BureauDeLaurent"
            Clef "Porte1" "BureauDeClement"
            Clef "Porte2" "BureauDeLaurent"
            JoueurHumain "Etudiant1" 10 10 "Couloir"
            ConditionDeFinVivantDansPiece SUCCES "Etudiant1" "BureauDesNicolas"
            ConditionDeFinVivantDansPiece ECHEC "Etudiant1" "BureauDuDirecteur"
        """;
        Reader reader = new StringReader(value);
        Simulateur simulateur = new Simulateur(reader);
        assertThat(simulateur.toString(), StringContains.containsString("BureauDesNicolas"));
        assertThat(simulateur.toString(), StringContains.containsString("serrure_2"));
        assertThat(simulateur.toString(), StringContains.containsString("BureauDuDirecteur"));
        assertThat(simulateur.toString(), StringContains.containsString("serrure_3"));
        assertThat(simulateur.toString(), StringContains.containsString("Couloir"));
        assertThat(simulateur.toString(), StringContains.containsString("clef_6"));
        assertThat(simulateur.toString(), StringContains.containsString("Porte3"));
        assertThat(simulateur.toString(), StringContains.containsString("clef_5"));
        assertThat(simulateur.toString(), StringContains.containsString("Porte4"));
        assertThat(simulateur.toString(), StringContains.containsString("BureauDeClement"));
        assertThat(simulateur.toString(), StringContains.containsString("Porte1"));
        assertThat(simulateur.toString(), StringContains.containsString("Etudiant1"));
        assertThat(simulateur.toString(), StringContains.containsString("Porte2"));
        assertThat(simulateur.toString(), StringContains.containsString("BureauDeLaurent"));
    }
}
