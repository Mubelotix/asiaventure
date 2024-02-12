package fr.insaRouen.iti.prog.asiaventure.elements;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;

public class PiedDeBiche extends Objet {
    public PiedDeBiche(String nom, Monde monde) {
        super(nom, monde);
    }

    public boolean estDeplacable() {
        return true;
    }
}
