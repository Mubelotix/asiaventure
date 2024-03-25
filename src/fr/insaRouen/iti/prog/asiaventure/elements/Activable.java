package fr.insaRouen.iti.prog.asiaventure.elements;

import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;

public interface Activable {
    /**
     * Retourne vrai si l'objet est activable avec l'objet obj, faux sinon.
     * @param obj L'objet avec lequel on veut activer l'objet
     */
    public boolean activableAvec(Objet obj);

    /**
     * Active l'objet
     * @throws ActivationException Si l'objet ne peut pas être activé
     */
    public void activer() throws ActivationException;

    /**
     * Active l'activable avec l'objet obj
     * @param obj L'objet avec lequel on veut activer l'objet
     * @throws ActivationException Si l'objet ne peut pas être activé
     */
    public void activerAvec(Objet obj) throws ActivationException;
}
