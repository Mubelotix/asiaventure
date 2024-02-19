package fr.insaRouen.iti.prog.asiaventure.elements.vivants;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;

class ObjetTest extends Objet {
    public ObjetTest(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    public boolean estDeplacable(){
        return false;
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
        assertThat(this.vivant.getObjets()[0].getNom(), IsEqual.equalTo("objet"));
        assertThat(this.vivant.getPiece().getNom(), IsEqual.equalTo("piece"));
    }

    @Test
    public void testObjets() throws Exception {
        assertThat(this.vivant.contientObjet(this.objet), Is.is(true));
        ObjetTest obj = new ObjetTest("tarte", this.monde);
        assertThat(this.vivant.contientObjet(obj), Is.is(false));
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
}
