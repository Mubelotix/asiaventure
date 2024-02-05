package fr.insaRouen.iti.prog.asiaventure.elements.structure;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;

public abstract class ElementStructurel extends Entite {
    public ElementStructurel(String nom, Monde monde) {
        super(nom, monde);
    }
}
