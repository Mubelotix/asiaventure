package fr.insaRouen.iti.prog.asiaventure.elements;

import fr.insaRouen.iti.prog.asiaventure.Monde;

public class PiedDeBiche extends Objet {
    public PiedDeBiche(String nom, Monde monde) {
        super(nom, monde);
    }

    public Boolean estDeplacable() {
        return true;
    }
}
