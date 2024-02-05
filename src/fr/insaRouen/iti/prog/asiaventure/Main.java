package fr.insaRouen.iti.prog.asiaventure;

import fr.insaRouen.iti.prog.asiaventure.elements.PiedDeBiche;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;

public class Main {
    public static void main(String[] args) {
        Monde monde = new Monde("monde");
        System.out.println(monde);

        PiedDeBiche pdb = new PiedDeBiche("pdb", monde);
        System.out.println(monde);
        assert pdb.estDeplacable();

        PiedDeBiche pdb2 = (PiedDeBiche)monde.getEntite("pdb");
        System.out.println(pdb2.toString());
        assert pdb2 == pdb;

        //ElementStructurel es = new ElementStructurel("es", monde);
        //System.out.println(monde);

        System.out.println("\nTest Entite");
        Main.testPiece();
    }

    public static void testPiece() {
        Monde monde = new Monde("monde");
        Piece piece1 = new Piece("piece1", monde);
        Piece piece2 = new Piece("piece2", monde);
        System.out.println(monde);

        // Attempt adding a Vivant to a Piece
        Vivant vivant1 = new Vivant("vivant1", monde, 42, 1, piece1);
        piece1.entrer(vivant1);
        assert piece1.contientVivant("vivant1");
        assert piece1.getObjets().length == 0;
        assert piece1.getVivants().length == 1;

        // Make sure we can't edit the array from the outside
        Vivant[] vivants = piece1.getVivants();
        vivants[0] = null;
        assert piece1.contientVivant("vivant1");
    }
}

class EntiteTest extends Entite {
    public EntiteTest(String nom, Monde monde){
        super(nom, monde);
    }
}
