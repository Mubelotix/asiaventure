package fr.insaRouen.iti.prog.asiaventure.elements.vivants;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.core.IsEqual;
import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Porte;

public class TestMonstre {
    @Test
    public void testMonstre() throws NomDEntiteDejaUtiliseDansLeMondeException {
        Monde monde = new Monde("monde");
        Objet obj = new ObjetTest("objet", monde);
        Objet obj2 = new ObjetTest("objet2", monde);
        Objet obj3 = new ObjetTest("objet3", monde);
        Piece piece = new Piece("piece", monde);
        piece.deposer(obj);
        Piece piece2 = new Piece("piece2", monde);
        piece2.deposer(obj2);
        piece2.deposer(obj3);
        new Porte("porte", monde, piece, piece2);
        Monstre monstre = new Monstre("monstre", monde, 3, 3, piece);
        
        monstre.executer();
        assertThat(monstre.getPiece().getNom(), IsEqual.equalTo("piece2"));
        assertThat(monstre.getPointsForce(), IsEqual.equalTo(3));
        assertThat(monstre.getPointsVie(), IsEqual.equalTo(2));
        assertThat(monstre.getObjets().size(), IsEqual.equalTo(2));

        monstre.executer();
        assertThat(monstre.getPiece().getNom(), IsEqual.equalTo("piece"));
        assertThat(monstre.getPointsVie(), IsEqual.equalTo(1));
        assertThat(monstre.getObjets().size(), IsEqual.equalTo(1));

        monstre.executer();
        assertThat(monstre.getPiece().getNom(), IsEqual.equalTo("piece2"));
        assertThat(monstre.getPointsVie(), IsEqual.equalTo(0));
        assertThat(monstre.getObjets().size(), IsEqual.equalTo(0));

        monstre.executer();
        assertThat(monstre.getPiece().getNom(), IsEqual.equalTo("piece2"));
        assertThat(monstre.getPointsVie(), IsEqual.equalTo(0));
        assertThat(monstre.getObjets().size(), IsEqual.equalTo(0));
    }
}
