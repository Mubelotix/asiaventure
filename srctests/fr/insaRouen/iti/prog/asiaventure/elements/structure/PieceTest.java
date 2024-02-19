package fr.insaRouen.iti.prog.asiaventure.elements.structure;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import fr.insaRouen.iti.prog.asiaventure.EntiteDejaDansUnAutreMondeException;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;
import static org.hamcrest.core.Is.is;

class ObjetTest extends Objet {
    public ObjetTest(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    public boolean estDeplacable(){
        return false;
    }
}

public class PieceTest {
    Monde monde;
    Piece piece1;
    Piece piece2;

    @Before
    public void initialisation() throws NomDEntiteDejaUtiliseDansLeMondeException {
        this.monde = new Monde("monde");
        this.piece1 = new Piece("piece1", this.monde);
        this.piece2 = new Piece("piece2", this.monde);
    }

    @Test
    public void ajouterVivant() throws Exception {
        Vivant vivant1 = new Vivant("vivant1", this.monde, 42, 1, this.piece1);
        this.piece1.entrer(vivant1);

        assertThat(this.piece1.contientVivant("vivant1"), is(true));
        assertThat(this.piece1.getVivants().length, is(1));
        assertThat(this.piece1.getObjets().length, is(0));
    }

    @Test(expected = NomDEntiteDejaUtiliseDansLeMondeException.class)
    public void ajouterVivantNomDejaUtilise() throws Exception {
        new Vivant("vivant1", this.monde, 42, 1, this.piece1);
        new Vivant("vivant1", this.monde, 42, 1, this.piece1);
    }

    @Test(expected = NomDEntiteDejaUtiliseDansLeMondeException.class)
    public void ajouterPieceNomDejaUtilise() throws Exception {
        new Piece("piece", this.monde);
        new Piece("piece", this.monde);
    }

    @Test(expected = EntiteDejaDansUnAutreMondeException.class)
    public void ajouterPieceDansAutreMonde() throws Exception {
        Monde monde2 = new Monde("monde2");
        monde2.ajouter(this.piece1);
    }

    @Test
    public void testVivantsIsCloned() throws Exception {
        Vivant vivant1 = new Vivant("vivant1", this.monde, 42, 1, this.piece1);
        this.piece1.entrer(vivant1);

        Vivant[] vivants = this.piece1.getVivants();
        vivants[0] = null;
        assertThat(this.piece1.contientVivant("vivant1"), is(true));
    }

    @Test
    public void testChangerVivantDePiece() throws Exception {
        Vivant vivant1 = new Vivant("vivant1", this.monde, 42, 1, this.piece1);
        this.piece1.entrer(vivant1);
        assertThat(this.piece1.contientVivant("vivant1"), is(true));
        assertThat(this.piece2.contientVivant("vivant1"), is(false));

        vivant1.entrer(piece2);
        assertThat(this.piece1.contientVivant("vivant1"), is(false));
        assertThat(this.piece2.contientVivant("vivant1"), is(true));
    }

    @Test
    public void deposerObjet() throws Exception {
        PiedDeBiche pdb1 = new PiedDeBiche("pdb1", this.monde);
        this.piece1.deposer(pdb1);
        assertThat(this.piece1.contientObjet("pdb1"), is(true));
        assertThat(this.piece1.getObjets().length, is(1));
    }

    @Test
    public void prendreObjet() throws Exception {
        PiedDeBiche pdb1 = new PiedDeBiche("pdb1", this.monde);
        this.piece1.deposer(pdb1);
        Vivant vivant1 = new Vivant("vivant1", this.monde, 42, 1, this.piece1);
        vivant1.prendre("pdb1");
        assertThat(this.piece1.contientObjet("pdb1"), is(false));
        assertThat(this.piece1.getObjets().length, is(0));
        assertThat(vivant1.getObjets().length, is(1));
    }

    @Test(expected = ObjetNonDeplacableException.class)
    public void prendreObjetNonDeplacable() throws Exception {
        ObjetTest obj1 = new ObjetTest("objetTest", this.monde);
        Vivant vivant1 = new Vivant("vivant1", this.monde, 42, 1, this.piece1);
        this.piece1.deposer(obj1);
        vivant1.prendre("objetTest");
    }

    @Test(expected = ObjetAbsentDeLaPieceException.class)
    public void prendreObjetAbsent() throws Exception {
        Vivant vivant1 = new Vivant("vivant1", this.monde, 42, 1, this.piece1);
        vivant1.prendre("pdb1");
    }

    @Test
    public void bougerObjetDePiece() throws Exception {
        PiedDeBiche pdb1 = new PiedDeBiche("pdb1", this.monde);
        Vivant vivant1 = new Vivant("vivant1", this.monde, 42, 1, this.piece1);
        this.piece1.deposer(pdb1);
        vivant1.entrer(piece1);
        vivant1.prendre("pdb1");
        vivant1.entrer(this.piece2);
        vivant1.deposer("pdb1");
        assertThat(this.piece1.contientObjet("pdb1"), is(false));
        assertThat(this.piece2.contientObjet("pdb1"), is(true));
        assertThat(vivant1.getObjets().length, is(0));
    }

    @Test(expected = ObjetNonPossedeParLeVivantException.class)
    public void deposerObjetNonPossede() throws Exception {
        PiedDeBiche pdb1 = new PiedDeBiche("pdb1", this.monde);
        Vivant vivant1 = new Vivant("vivant1", this.monde, 42, 1, this.piece1);
        vivant1.deposer(pdb1);
    }
}
