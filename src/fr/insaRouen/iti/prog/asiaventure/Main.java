package fr.insaRouen.iti.prog.asiaventure;

import fr.insaRouen.iti.prog.asiaventure.elements.PiedDeBiche;
import fr.insaRouen.iti.prog.asiaventure.elements.structure.ElementStructurel;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;

public class Main {
    public static void main(String[] args) {
        Monde monde = new Monde("monde");
        System.out.println(monde);

        PiedDeBiche pdb = new PiedDeBiche("pdb", monde);
        System.out.println(monde);
        assert pdb.estDeplacable();

        Entite pdb2 = monde.getEntite("pdb");
        assert pdb2 == pdb;

        ElementStructurel es = new ElementStructurel("es", monde);
        System.out.println(monde);
    }
}
