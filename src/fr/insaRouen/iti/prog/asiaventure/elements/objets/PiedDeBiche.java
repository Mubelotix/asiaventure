package fr.insaRouen.iti.prog.asiaventure.elements.objets;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

public class PiedDeBiche extends Objet {
    public PiedDeBiche(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    @Override
    public boolean estDeplacable() {
        return true;
    }

    public String toString() {
        return String.format("PiedDeBiche(nom: %s, monde: %s)", this.getNom(), this.getMonde().getNom());
    }
}
