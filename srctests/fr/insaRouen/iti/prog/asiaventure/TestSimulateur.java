package fr.insaRouen.iti.prog.asiaventure;

import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.StringReader;

import org.hamcrest.core.IsEqual;
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
        assertThat(simulateur.toString(), StringContains.containsString("BureauDuDirecteur"));
        assertThat(simulateur.toString(), StringContains.containsString("Couloir"));
        assertThat(simulateur.toString(), StringContains.containsString("Porte3"));
        assertThat(simulateur.toString(), StringContains.containsString("Porte4"));
        assertThat(simulateur.toString(), StringContains.containsString("BureauDeClement"));
        assertThat(simulateur.toString(), StringContains.containsString("Porte1"));
        assertThat(simulateur.toString(), StringContains.containsString("Etudiant1"));
        assertThat(simulateur.toString(), StringContains.containsString("Porte2"));
        assertThat(simulateur.toString(), StringContains.containsString("BureauDeLaurent"));
    }

    @Test
    public void testSerialization() throws ASIAventureException, IOException, ClassNotFoundException {
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
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        simulateur.enregister(oos);
        oos.close();
        
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        Simulateur simulateur2 = new Simulateur(ois);
        assertThat(simulateur2.toString(), IsEqual.equalTo(simulateur.toString()));
    }
}
