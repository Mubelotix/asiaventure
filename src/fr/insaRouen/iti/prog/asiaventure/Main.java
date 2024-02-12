package fr.insaRouen.iti.prog.asiaventure;

import fr.insaRouen.iti.prog.asiaventure.elements.PiedDeBiche;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ElementStructurel;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;

public class Main {
    public static void main(String[] args) {
        Monde monde = new Monde("monde");
        System.out.println(monde);

        PiedDeBiche pdb = new PiedDeBiche("pdb", monde);
        System.out.println(monde);
        assert pdb.estDeplacable();

        EntiteTest pdb2 = new EntiteTest("nom",monde);
        System.out.println(pdb2.toString());

        ElementStructurelTest es = new ElementStructurelTest("es", monde);
        System.out.println(es);

        testPiece();
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

        // Attempt to move the Vivant to another Piece
        vivant1.entrer(piece2);
        assert !piece1.contientVivant("vivant1");
        assert piece2.contientVivant("vivant1");

        // Same thing with an Objet
        ObjetTest objet1 = new ObjetTest("objet1", monde);
        piece1.deposer(objet1);
        assert piece1.contientObjet("objet1");
        assert piece1.getObjets().length == 1;
        
        // Pick up the Objet
        vivant1.entrer(piece1);
        vivant1.prendre("objet1");
        assert !piece1.contientObjet("objet1");
        assert piece1.getObjets().length == 0;
        assert vivant1.getObjets().length == 1;

        // Move the Objet to another Piece
        vivant1.entrer(piece2);
        vivant1.deposer("objet1");
        assert !piece1.contientObjet("objet1");
        assert piece2.contientObjet("objet1");
        assert vivant1.getObjets().length == 0;
    }
}

class EntiteTest extends Entite{
    public EntiteTest(String nom, Monde monde){
        super(nom, monde);
    }
}

class ObjetTest extends Objet{
    public ObjetTest(String nom, Monde monde){
        super(nom, monde);
    }

    public boolean estDeplacable(){
        return false;
    }
}

class VivantTest extends Vivant{
    public VivantTest(String nom, Monde monde,  int pointsVie, int pointsForce, Piece piece, Objet... objets){
        super(nom, monde, pointsVie, pointsForce, piece, objets);
    }
}

class ElementStructurelTest extends ElementStructurel{
    public ElementStructurelTest(String nom, Monde monde){
        super(nom, monde);
    }
}
