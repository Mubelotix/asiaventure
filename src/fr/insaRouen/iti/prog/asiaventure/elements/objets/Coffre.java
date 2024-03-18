 package fr.insaRouen.iti.prog.asiaventure.elements.objets;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.Activable;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationException;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insaRouen.iti.prog.asiaventure.elements.ActivationImpossibleException;
import fr.insaRouen.iti.prog.asiaventure.elements.Etat;

public class Coffre extends Objet implements Activable {
    private Etat etat = Etat.FERME;

    public Coffre(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    @Override
    public boolean estDeplacable() {
        return false;
    }

    public boolean activableAvec(Objet obj) {
        return false;
    }

    public void activer() throws ActivationException {
        if (this.etat == Etat.FERME) {
            this.etat = Etat.OUVERT;
        } else {
            throw new ActivationImpossibleException(String.format("L'Activable %s a déjà été activé", this.getNom()));
        }
    }

    public void activerAvec(Objet obj) throws ActivationException {
        if (this.activableAvec(obj)) {
            this.activer();
        } else {
            throw new ActivationImpossibleAvecObjetException(String.format("L'Activable %s n'est pas activable avec l'objet %s", this.getNom(), obj.getNom()));
        }
    }

    public Etat getEtat() {
        return this.etat;
    }

    public String toString() {
        return String.format("Coffre(nom: %s, monde: %s, etat: %s)", this.getNom(), this.getMonde().getNom(), this.getEtat());
    }
}
