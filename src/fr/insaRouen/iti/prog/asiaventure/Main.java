package fr.insaRouen.iti.prog.asiaventure;

import fr.insaRouen.iti.prog.asiaventure.elements.PiedDeBiche;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ElementStructurel;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.Piece;
import fr.insaRouen.iti.prog.asiaventure.elements.vivants.Vivant;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;
import fr.insaRouen.iti.prog.asiaventure.elements.Objet;



public class Main {
    public static void main(String[] args) {
        Monde monde = new Monde("monde");
        System.out.println(monde);

        PiedDeBiche pdb = new PiedDeBiche("pdb", monde);
        System.out.println(monde);
        assert pdb.estDeplacable();

        EntiteTest pdb2 = new EntiteTest("nom",monde);
        System.out.println(pdb2.toString());
        //assert pdb2 == pdb;

        ElementStructurelTest es = new ElementStructurelTest("es", monde);
        System.out.println(monde);
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

    public Boolean estDeplacable(){
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

