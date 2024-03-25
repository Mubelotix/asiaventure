package fr.insaRouen.iti.prog.asiaventure.elements.structure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Before;
import org.junit.Test;

import fr.insaRouen.iti.prog.asiaventure.ASIAventureException;
import fr.insaRouen.iti.prog.asiaventure.EntiteDejaDansUnAutreMondeException;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;

import static org.hamcrest.core.Is.is;

import java.util.Collection;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import java.lang.UnsupportedOperationException;

class ObjetTest extends Objet {
    public ObjetTest(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    public boolean estDeplacable(){
        return false;
    }
}

public class TestPiece {
    Monde monde;
    Piece piece1;
    Piece piece2;
    Piece piece3;

    @Before
    public void initialisation() throws NomDEntiteDejaUtiliseDansLeMondeException {
        this.monde = new Monde("monde");
        this.piece1 = new Piece("piece1", this.monde);
        this.piece2 = new Piece("piece2", this.monde);
        this.piece3 = new Piece("piece3", this.monde);

    }

    @Test
    public void ajouterVivant() throws NomDEntiteDejaUtiliseDansLeMondeException {
        Vivant vivant1 = new Vivant("vivant1", this.monde, 42, 1, this.piece1);
        this.piece1.entrer(vivant1);

        assertThat(this.piece1.contientVivant("vivant1"), is(true));
        assertThat(this.piece1.getVivants().size(), is(1));
        assertThat(this.piece1.getObjets().isEmpty(), is(true));
    }

    @Test(expected = NomDEntiteDejaUtiliseDansLeMondeException.class)
    public void ajouterVivantNomDejaUtilise() throws NomDEntiteDejaUtiliseDansLeMondeException {
        new Vivant("vivant1", this.monde, 42, 1, this.piece1);
        new Vivant("vivant1", this.monde, 42, 1, this.piece1);
    }

    @Test(expected = NomDEntiteDejaUtiliseDansLeMondeException.class)
    public void ajouterPieceNomDejaUtilise() throws NomDEntiteDejaUtiliseDansLeMondeException {
        new Piece("piece", this.monde);
        new Piece("piece", this.monde);
    }

    @Test(expected = EntiteDejaDansUnAutreMondeException.class)
    public void ajouterPieceDansAutreMonde() throws NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException {
        Monde monde2 = new Monde("monde2");
        monde2.ajouter(this.piece1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testVivantsIsUnmodifiable() throws ASIAventureException, UnsupportedOperationException {
        Vivant vivant1 = new Vivant("vivant1", this.monde, 42, 1, this.piece1);
        this.piece1.entrer(vivant1);

        Collection<Vivant> vivants = this.piece1.getVivants();
        vivants.remove(vivant1);
        assertThat(this.piece1.contientVivant("vivant1"), is(true));
    }

    @Test
    public void testGetPorte() throws NomDEntiteDejaUtiliseDansLeMondeException, PorteInexistanteDansLaPieceException {
        Porte porte = new Porte("porte", this.monde, this.piece1, this.piece2);
        assertThat(this.piece1.getPorte("porte"), equalTo(porte));
    }

    @Test
    public void testChangerVivantDePiece() throws NomDEntiteDejaUtiliseDansLeMondeException {
        Vivant vivant1 = new Vivant("vivant1", this.monde, 42, 1, this.piece1);
        this.piece1.entrer(vivant1);
        assertThat(this.piece1.contientVivant("vivant1"), is(true));
        assertThat(this.piece2.contientVivant("vivant1"), is(false));

        vivant1.entrer(piece2);
        assertThat(this.piece1.contientVivant("vivant1"), is(false));
        assertThat(this.piece2.contientVivant("vivant1"), is(true));
    }

    @Test
    public void deposerObjet() throws NomDEntiteDejaUtiliseDansLeMondeException {
        PiedDeBiche pdb1 = new PiedDeBiche("pdb1", this.monde);
        this.piece1.deposer(pdb1);
        assertThat(this.piece1.contientObjet("pdb1"), is(true));
        assertThat(this.piece1.getObjets().size(), is(1));
    }

    @Test
    public void bougerObjetDePiece() throws NomDEntiteDejaUtiliseDansLeMondeException, ObjetAbsentDeLaPieceException, ObjetNonPossedeParLeVivantException, ObjetNonDeplacableException {
        PiedDeBiche pdb1 = new PiedDeBiche("pdb1", this.monde);
        Vivant vivant1 = new Vivant("vivant1", this.monde, 42, 1, this.piece1);
        this.piece1.deposer(pdb1);
        vivant1.entrer(piece1);
        vivant1.prendre("pdb1");
        vivant1.entrer(this.piece2);
        vivant1.deposer("pdb1");
        assertThat(this.piece1.contientObjet("pdb1"), is(false));
        assertThat(this.piece2.contientObjet("pdb1"), is(true));
        assertThat(vivant1.getObjets().isEmpty(), is(true));
    }

    @Test
    public void TestContientPorte() throws NomDEntiteDejaUtiliseDansLeMondeException{
        Porte p1 = new Porte("porte10", this.monde, piece1, piece2);
        assertThat(this.piece1.aLaPorte(p1), is(true));
        assertThat(this.piece2.aLaPorte(p1.getNom()), is(true));
        assertThat(this.piece3.aLaPorte(p1), is(false));
        assertThat(this.piece3.aLaPorte(p1.getNom()), is(false));
        this.piece3.addPorte(p1);
        assertThat(this.piece3.aLaPorte(p1), is(true));
    }
}
