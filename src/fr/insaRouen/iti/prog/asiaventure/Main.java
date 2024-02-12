package fr.insaRouen.iti.prog.asiaventure;

import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.PiedDeBiche;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ElementStructurel;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;

public class Main {
    public static void main(String[] args) throws ASIAventureException {
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

    public static void testPiece() throws ASIAventureException {
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
        PiedDeBiche pdb1 = new PiedDeBiche("pdb1", monde);
        piece1.deposer(pdb1);
        assert piece1.contientObjet("pdb1");
        assert piece1.getObjets().length == 1;
        
        // Pick up the Objet
        vivant1.entrer(piece1);
        vivant1.prendre("pdb1");
        assert !piece1.contientObjet("pdb1");
        assert piece1.getObjets().length == 0;
        assert vivant1.getObjets().length == 1;

        // Essayer pareil avec un objet non déplaçable et s'assurer de l'exception
        ObjetTest obj1 = new ObjetTest("objetTest", monde);
        piece1.deposer(obj1);
        assert piece1.contientObjet("objetTest");
        assert piece1.getObjets().length == 1;
        assert !obj1.estDeplacable();
        try {
            vivant1.prendre("objetTest");
            assert false : "L'objet n'est pas déplaçable mais a été pris";
        } catch (ObjetNonDeplacableException e) {}

        // Esayer de prendre un objet qui n'est pas dans la pièce
        try {
            vivant1.prendre("pdb1");
            assert false : "L'objet n'est pas dans la pièce mais a été pris";
        } catch (ObjetAbsentDeLaPieceException e) {}

        // Move the Objet to another Piece
        vivant1.entrer(piece2);
        vivant1.deposer("pdb1");
        assert !piece1.contientObjet("pdb1");
        assert piece2.contientObjet("pdb1");
        assert vivant1.getObjets().length == 0;

        // Essayer de déposer un objet qu'on ne possède pas
        try {
            vivant1.deposer("pdb1");
            assert false : "L'objet n'est pas possédé par le vivant mais a été déposé";
        } catch (ObjetNonPossedeParLeVivantException e) {}
    }
}

class EntiteTest extends Entite{
    public EntiteTest(String nom, Monde monde) throws ASIAventureException{
        super(nom, monde);
    }
}

class ObjetTest extends Objet{
    public ObjetTest(String nom, Monde monde) throws ASIAventureException{
        super(nom, monde);
    }

    public boolean estDeplacable(){
        return false;
    }
}

class VivantTest extends Vivant{
    public VivantTest(String nom, Monde monde,  int pointsVie, int pointsForce, Piece piece, Objet... objets) throws ASIAventureException{
        super(nom, monde, pointsVie, pointsForce, piece, objets);
    }
}

class ElementStructurelTest extends ElementStructurel{
    public ElementStructurelTest(String nom, Monde monde) throws ASIAventureException{
        super(nom, monde);
    }
}
