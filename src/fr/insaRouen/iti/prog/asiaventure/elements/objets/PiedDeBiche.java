package fr.insaRouen.iti.prog.asiaventure.elements.objets;

import fr.insaRouen.iti.prog.asiaventure.Monde;

public class PiedDeBiche extends Objet {
    public PiedDeBiche(String nom, Monde monde) {
        super(nom, monde);
    }

    public boolean estDeplacable() {
        return true;
    }
}
