package fr.insaRouen.iti.prog.asiaventure.elements.vivants;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Porte;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.PorteFermeException;

class ObjetTest extends Objet {
    public boolean deplacable = false;

    public ObjetTest(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    public boolean estDeplacable(){
        return this.deplacable;
    }
}

class VivantTest extends Vivant{
    public VivantTest(String nom, Monde monde,  int pointsVie, int pointsForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde, pointsVie, pointsForce, piece, objets);
    }
}

public class TestVivant{
    Monde monde;
    ObjetTest objet;
    VivantTest vivant;
    Piece piece;

    @Before
    public void initialisation() throws NomDEntiteDejaUtiliseDansLeMondeException {
        this.monde = new Monde("monde");
        this.objet = new ObjetTest("objet", this.monde);
        this.piece = new Piece("piece", this.monde);
        this.vivant = new VivantTest("vivant", this.monde, 15, 14, this.piece, this.objet);
    }

    @Test
    public void testEgalite(){
        assertThat(this.vivant.getNom(), IsEqual.equalTo("vivant"));
        assertThat(this.vivant.getPointsForce(), IsEqual.equalTo(14));
        assertThat(this.vivant.getPointsVie(), IsEqual.equalTo(15));
        assertThat(this.vivant.getObjets().get("objet"), IsEqual.equalTo(this.objet));
        assertThat(this.vivant.getPiece().getNom(), IsEqual.equalTo("piece"));
    }

    @Test
    public void testObjets() throws Exception {
        assertThat(this.vivant.possede(this.objet), Is.is(true));
        ObjetTest obj = new ObjetTest("tarte", this.monde);
        assertThat(this.vivant.possede(obj), Is.is(false));
    }

    @Test
    public void testVivantFranchitPorte() throws Exception {
        Monde monde = new Monde("monde");
        Piece pA = new Piece("pA", monde);
        Piece pB = new Piece("pB", monde);
        Porte porte = new Porte("porte", monde, pA, pB);
        VivantTest vivant = new VivantTest("vivant", monde, 15, 14, pA);

        porte.activer(); // Ouvrir la porte
        assertThat(vivant.getPiece(), equalTo(pA));
        vivant.franchir(porte);
        assertThat(vivant.getPiece(), equalTo(pB));
    }

    @Test(expected = PorteFermeException.class)
    public void testVivantFranchitPorteFermee() throws Exception {
        Monde monde = new Monde("monde");
        Piece pA = new Piece("pA", monde);
        Piece pB = new Piece("pB", monde);
        Porte porte = new Porte("porte", monde, pA, pB);
        VivantTest vivant = new VivantTest("vivant", monde, 15, 14, pA);

        assertThat(vivant.getPiece(), equalTo(pA));
        vivant.franchir(porte);
    }

    @Test
    public void testSortirEntrer(){
        this.vivant.sortir();
        assertThat(this.vivant.getPiece(),IsNull.nullValue());
        this.vivant.sortir();
        assertThat(this.vivant.getPiece(),IsNull.nullValue());
        this.vivant.entrer(this.piece);
        assertThat(this.vivant.getPiece(),IsEqual.equalTo(this.piece));
    }

    @Test
    public void prendreObjet() throws NomDEntiteDejaUtiliseDansLeMondeException, ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        PiedDeBiche pdb1 = new PiedDeBiche("pdb1", this.monde);
        this.piece.deposer(pdb1);
        this.vivant.prendre("pdb1");
        assertThat(this.piece.contientObjet("pdb1"), Is.is(false));
        assertThat(this.piece.getObjets().isEmpty(), Is.is(true));
        assertThat(this.vivant.getObjets().size(), Is.is(2));
    }

    @Test(expected = ObjetNonDeplacableException.class)
    public void prendreObjetNonDeplacable() throws NomDEntiteDejaUtiliseDansLeMondeException, ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        ObjetTest obj1 = new ObjetTest("objetTest", this.monde);
        this.piece.deposer(obj1);
        this.vivant.prendre("objetTest");
    }

    @Test(expected = ObjetAbsentDeLaPieceException.class)
    public void prendreObjetAbsent() throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
        this.vivant.prendre("pdb1");
    }

    @Test(expected = ObjetNonPossedeParLeVivantException.class)
    public void deposerObjetNonPossede() throws NomDEntiteDejaUtiliseDansLeMondeException, ObjetNonPossedeParLeVivantException {
        PiedDeBiche pdb1 = new PiedDeBiche("pdb1", this.monde);
        this.vivant.deposer(pdb1);
    }
}
