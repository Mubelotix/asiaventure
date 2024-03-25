package fr.insaRouen.iti.prog.asiaventure.elements.objets.serrurerie;

import fr.insaRouen.iti.prog.asiaventure.Monde;
import fr.insaRouen.iti.prog.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insaRouen.iti.prog.asiaventure.elements.objets.Objet;


public final class Clef extends Objet {
    private static int numero;

    protected Clef(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException {
        super(nom, monde);
    }

    @Override
    public boolean estDeplacable() {
        return true;
    }

    public String toString() {
        return String.format("Clef(nom: %s, monde: %s)", this.getNom(), this.getMonde().getNom());
    }

    protected static Clef creer(Monde monde){
        numero++;
        try {
            return new Clef(String.format("clef_%d",numero), monde);
        } catch (NomDEntiteDejaUtiliseDansLeMondeException e) {
            return Clef.creer(monde);
        }
    }

}
