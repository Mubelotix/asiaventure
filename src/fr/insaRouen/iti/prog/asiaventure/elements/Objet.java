package fr.insaRouen.iti.prog.asiaventure.elements;

import fr.insaRouen.iti.prog.asiaventure.Monde;

public class Objet extends Entite {
    public Objet(String nom, Monde monde) {
        super(nom, monde);
    }

    public Boolean estDeplacable() {
        return false;
    }
}
