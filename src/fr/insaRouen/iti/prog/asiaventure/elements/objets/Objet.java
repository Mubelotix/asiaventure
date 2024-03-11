package fr.insaRouen.iti.prog.asiaventure.elements.objets;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.Entite;

/**
 * Classe abstraite représentant un objet dans le monde de jeu.
 */
public abstract class Objet extends Entite {
    public Objet(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    /**
     * Indique si l'objet est déplaçable.
     * @return Vrai si l'objet est déplaçable, faux sinon.
     */
    public abstract boolean estDeplacable();
}
