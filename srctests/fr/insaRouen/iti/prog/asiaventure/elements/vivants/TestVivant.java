package fr.insaRouen.iti.prog.asiaventure.elements.vivants;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.VivantAbsentDeLaPieceException;


class TestVivant extends Vivant{
    public TestVivant(String nom, Monde monde,  int pointsVie, int pointsForce, Piece piece, Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde, pointsVie, pointsForce, piece, objets);
    }

}

public class VivantTest{
    Monde monde;
    Objet objet;
    TestVivant vivant;
    Piece piece;


    @Before
    public void initialisation() throws NomDEntiteDejaUtiliseDansLeMondeException {
        this.monde = new Monde("monde");
        this.objet = new Objet("objet", this.monde);
        this.piece = new Piece("piece", this.monde);
        this.nom = "humain":
        this.pointsForce = 14;
        this.pointsVie = 15;

    }

    @Test
    public void testVivant(){
        
    }


}