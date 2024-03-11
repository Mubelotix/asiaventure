package fr.insaRouen.iti.prog.asiaventure.elements;

import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;

public interface Activable {
    public boolean activableAvec(Objet obj);
    public void activer() throws ActivationException;
    public void activerAvec(Objet obj) throws ActivationException;
}
